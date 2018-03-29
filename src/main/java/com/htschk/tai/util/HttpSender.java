package com.htschk.tai.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * Created by qikai.yu on 2016/4/28.
 */
@Component
@Scope("prototype")
public class HttpSender {

    @Value("${external.httpReadTimeout:10000}")
    private int readTimeOut = 10000;

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public void start() {

    }

    public void stop() {

    }

    public String sendHttpRequest(String url, String param) {
        if (param != null) {
            return post(url, param, null);
        } else {
            return get(url, null, null);
        }


    }

    public String sendHttpRequest(String url, String param, Map<String, String> headers) {
        long t1 = System.currentTimeMillis();
        String responseString = post(url, param, headers);
        long t2 = System.currentTimeMillis();
        LogManager.infoSystemLog((t2 - t1) + "ms , for HTTP request:" + url);
        return responseString;
    }

    private synchronized String post(String uri, String request, Map<String, String> headers) {
        try {
            HttpURLConnection connection = getConnection(uri, "POST");
            if (headers != null) {
                headers.entrySet().forEach(it ->
                        connection.setRequestProperty(it.getKey(), it.getValue())
                );
            }
            connection.setReadTimeout(readTimeOut);
            connection.connect();
            BufferedOutputStream outputStream = new BufferedOutputStream(new DataOutputStream(connection.getOutputStream()));
            outputStream.write(request.getBytes());
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            connection.disconnect();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized String get(String uri, String request, Map<String, String> headers) {
        try {
            HttpURLConnection connection = getConnection(uri, "GET");
            if (headers != null) {
                headers.entrySet().forEach(it ->
                        connection.setRequestProperty(it.getKey(), it.getValue())
                );
            }
            connection.setReadTimeout(readTimeOut);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            connection.disconnect();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    private HttpURLConnection getConnection(String uri, String method) throws IOException {
        HttpURLConnection connection;
        URL url = new URL(uri);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    private void downloadFile(String uri, String filePath) {
        try {
            HttpURLConnection connection;
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setReadTimeout(readTimeOut);
            connection.connect();

            InputStream is = connection.getInputStream();

            File localFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(localFile);

            byte[] bytes = new byte[1024 * 50];

            int i = is.read(bytes);

            while (i > 0) {
                fos.write(bytes, 0, i);
                i = is.read(bytes);
            }

            is.close();
            fos.close();

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}