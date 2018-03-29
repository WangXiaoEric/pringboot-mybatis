package com.htschk.tai.util;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpSenderUtil {

    public String sendRequest(String url) {
        return null;
    }


    public static String getCookieByName(HttpServletRequest request, String name) {
        Map cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public static Cookie getCookieObjectByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
            for(Cookie cookie:cookies) {
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        HashMap cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public static String loadJson(String url) {
        StringBuilder json = new StringBuilder();

        try {
            URL e = new URL(url);
            URLConnection uc = e.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;

            while((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }

            in.close();
        } catch (MalformedURLException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return json.toString();
    }

}
