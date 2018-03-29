package com.htschk.tai.util;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.model.CommonConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/21.
 */
public class PpBeanUtil {
    //    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
    private static Integer INTEGER_ONE = new Integer(1);

    public static Boolean doubleEquals(Double d1, Double d2) {
        if (Math.abs(d1 - d2) > CommonConstant.ZERO_STANDARD) {//不相等
            return false;
        } else {
            return true;
        }
    }


    public static String getAreaDisplay(String provinceValue, String cityValue, String districtValue) {
        String area = (provinceValue == null ? "" : provinceValue) + "/" +
                (cityValue == null ? " " : cityValue) + "/" +
                (districtValue == null ? "" : districtValue);
        if (area.endsWith("//")) {
            area = area.substring(0, area.length() - 2);
        }
        return area;
    }

    public static String formatDoubleWithTwoDigital(Double d) {
        if (d == null)
            return "0.00";
        DecimalFormat formatter = new DecimalFormat("#.##");
        return formatter.format(d);
    }

    public static String formatDoubleWithTwoDigitalForIntegration(Double d) {
        if (d == null)
            return "0.00";
        DecimalFormat formatter = new DecimalFormat("#.00");
        return formatter.format(d);
    }

    public static String getStringValueFromMap(Map map, String fieldName) {
        return map.get(fieldName) != null ? map.get(fieldName).toString() : "";
    }

    public static String convertBoolStr(Boolean b) {
        return b == null ? "0" : (b ? "1" : "0");
    }

    public static Map<String, Object> getResultMapSuccess(List<Map<String, Object>> detailList) {
        Map<String, Object> map = new HashMap<>();
        map.put(CommonConstant.RETURN_CODE, "0");
        map.put(CommonConstant.RETURN_MSG, "Success");
        int count = detailList.size();
        map.put(CommonConstant.RESULT_COUNT, count);
        map.put(CommonConstant.RESULT, detailList);
        return map;
    }

    public static String getPropertyFromObject(Object object, String propertyName) {
        try {
            return BeanUtils.getProperty(object, propertyName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return CommonConstant.BLANK;
    }

    public static void populateProperties(Object object, Map propMap) {
        try {
            BeanUtils.populate(object, propMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void populateProperties(Object object, Object valueObj) {
        try {
            BeanUtils.copyProperties(object, valueObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static <T> String collectionToString(Collection<T> collection) {
        if (collection != null && collection.size() > 0) {
            StringBuilder sb = new StringBuilder();
            collection.forEach(it -> {
                sb.append(",").append(it);
            });
            return sb.substring(1);
        } else {
            return "";
        }
    }

    public static Set<Long> stringToLongSet(String str) {
        Set<Long> set = new HashSet<>();
        if (StringUtils.isNotEmpty(str)) {
            try {
                for (String s : str.split(",")) {
                    set.add(Long.parseLong(s));
                }
            } catch (Exception e) {
                //exception log
                e.printStackTrace();
            }
        }
        return set;
    }


    public static List<String> stringToCollection(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return Collections.emptyList();
        } else {
            String[] strs = ids.split(",");
            return new ArrayList<>(Arrays.asList(strs));
        }
    }

    //todo:后续和 Long 合并
//    public static String collectionStringToString(Collection<String> collection) {
//        if (collection != null && collection.size() > 0) {
//            StringBuilder sb = new StringBuilder();
//            collection.forEach(it -> {
//                sb.append(",").append(it);
//            });
//            return sb.substring(1);
//        } else {
//            return "";
//        }
//    }

    /**
     * 给一个对象的某个属性设值，只限String，其他类型需要转换
     *
     * @param obj
     * @param propertyName
     * @param map
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setPropertyForObject(Object obj, String propertyName, Map map) {
        setPropertyAndThrowException(obj, propertyName, map.get(propertyName));
    }

    /**
     * 给一个对象的部分属性设值
     *
     * @param object
     * @param propertyNames
     * @param map
     */
    public static void setPropertiesForObject(Object object, String[] propertyNames, Map map) {
        for (String propertyName : propertyNames) {
            setPropertyAndThrowException(object, propertyName, map.get(propertyName));
        }
    }


    public static void setPropertyAndThrowException(Object object1, String propertyName, Object object2) throws TaiException {
        if (object2 != null) {
            try {
                BeanUtils.setProperty(object1, propertyName, object2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new TaiException(object1.getClass().getName() + "类中的" + propertyName + "属性的set方法为private");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new TaiException("在执行" + object1.getClass().getName() + "类中的" + propertyName + "属性的set方法时发生异常");
            }
        }
    }


    public static void setListStringForObj(Object object, String[] propertyNames, Map map) {
        for (String propertyName : propertyNames) {
            Object obj = map.get(propertyName);
            if (obj != null) {
                if (obj instanceof List) {
                    setPropertyAndThrowException(object, propertyName, obj);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(obj.toString());
                    setPropertyAndThrowException(object, propertyName, list);
                }
            }
        }
    }

    public static List<Long> toListLong(List<String> list) {
        List<Long> long_list = new ArrayList<>();
        if (list != null) {
            for (String str : list) {
                if (isInt(str)) {
                    long_list.add(Long.parseLong(str));
                } else {
                    throw new TaiException("toLongListException : List<String> cast to List<Long> failed!");
                }
            }
        }
        return long_list;
    }


    /**
     * 判断是否是正整数
     *
     * @param str
     * @return
     */
    private static boolean isInt(String str) {
        char[] numChars = str.toCharArray();
        for (char numChar : numChars) {
            if (!Character.isDigit(numChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将List<String>用制定的join连接起来，转换成字符串
     *
     * @param strList
     * @param join
     * @return
     */
    public static String listToString(List<String> strList, String join) {
        String result = "";
        if (strList != null) {
            for (String str : strList) {
                result += "".equals(result) ? str : join + str;
            }
        }
        return result;
    }

    public static String listToStringForInCriteria(List<String> strList, String join) {
        String result = "";
        if (strList != null) {
            for (String str : strList) {
                result += "".equals(result) ? "'" + str + "'" : join + "'" + str + "'";
            }
        }
        return result;
    }

    /**
     * 将String按指定split分割成数组，再放入一个集合中
     *
     * @param str
     * @param split
     * @return
     */
    public static List<String> stringToList(String str, String split) {
        if (StringUtils.isEmpty(str)) {
            return Collections.EMPTY_LIST;
        }
        String[] stringArray = str.split(split);
        List<String> list = new ArrayList<String>();
        for (String string : stringArray) {
            list.add(string);
        }
        return list;
    }

    public static String getFormattedDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(CommonConstant.DATE_FORMAT).format(date);
    }

    //    public static Map<String, List<String>> getListForAddAndListForReduce(List<String> newList, List<String> oldList) {
//        Map<String, List<String>> map = new HashMap<>();
//        if (newList != null) {
//            for (int i = 0; i < newList.size(); i++) {
//                if (oldList != null) {
//                    for (int j = 0; j < oldList.size(); j++) {
//                        if (newList.get(i).equals(oldList.get(j))) {
//                            newList.remove(i);
//                            oldList.remove(j);
//                            i--;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        map.put("listForAdd", newList);
//        map.put("listForReduce", oldList);
//        return map;
//    }
//
//
    public static List<String> getFileContent(String uri, String charset) {
        List<String> list = new ArrayList<>();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;

        try {
            inputStream = PpBeanUtil.class.getResourceAsStream(uri);
            inputStreamReader = new InputStreamReader(inputStream, StringUtils.isNotEmpty(charset) ? charset : "utf-8");
            reader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static List<String> getFileContentInFile(String file) {
        return getFileContentInFile(new File(file));
    }

    public static List<String> getFileContentInFile(File file) {
        List<String> list = new ArrayList<>();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            reader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean hasDigital(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static String processStringWithLength(String s, Integer length) {
        if (s != null) {
            if (s.length() > length) {
                return s.substring(0, length);
            } else {
                return s;
            }

        } else {
            return s;
        }


    }


    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static Integer stringToInteger(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        Integer i = null;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    /*
        避免使用 org.apache.commons.collections.CollectionUtils 在测试案例中出现 security 异常, 将实现移入本系统工具类中
     */
    public static Collection intersection(Collection a, Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();

        while (it.hasNext()) {
            Object obj = it.next();
            int i = 0;

            for (int m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; ++i) {
                list.add(obj);
            }
        }

        return list;
    }

    private static Map getCardinalityMap(Collection coll) {
        Map count = new HashMap();
        Iterator it = coll.iterator();

        while (it.hasNext()) {
            Object obj = it.next();
            Integer c = (Integer) ((Integer) count.get(obj));
            if (c == null) {
                count.put(obj, INTEGER_ONE);
            } else {
                count.put(obj, new Integer(c.intValue() + 1));
            }
        }

        return count;
    }

    private static final int getFreq(Object obj, Map freqMap) {
        Integer count = (Integer) freqMap.get(obj);
        return count != null ? count.intValue() : 0;
    }

}
