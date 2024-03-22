package com.platform.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 名称：StringUtils <br>
 * 描述：String工具类<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class StringUtils {
    public static final String EMPTY = "";
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 判断字符串是否不为空，不为空则返回true
     *
     * @param str 源数据
     * @return Boolean
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
     * Object 对象转换成字符串
     * @param obj
     * @return
     */
    public static String toStringByObject(Object obj){
        return toStringByObject(obj,false,null);
    }
    /**
     * Object 对象转换成字符串，并可以根据参数去掉两端空格
     * @param obj
     * @return
     */
    public static String toStringByObject(Object obj, boolean isqdkg, String datatype){
        if(obj==null){
            return "";
        }else{
            if(isqdkg){
                return obj.toString().trim();
            }else{
                //如果有设置时间格式类型，这转换
                if(StringUtils.hasText(datatype)){
                    if(obj instanceof Timestamp){
                        return DateUtils.format((Timestamp)obj,datatype);
                    }else if(obj instanceof Date){
                        return DateUtils.format((Timestamp)obj,datatype);
                    }
                }
                return obj.toString();
            }


        }
    }
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence)str);
    }

    public static int parseInt(Object str) {
        return parseInt(str, 0);
    }

    public static int parseInt(Object str, int defaultValue) {
        if (str == null || str.equals("")) {
            return defaultValue;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+")) {
            return defaultValue;
        }
        return Integer.parseInt(s);
    }

    /**
     * 将国际时间格式：yyyy-MM-dd'T'HH:mm:ss.S'Z' 格式化 为北京时间（时：分）
     *
     * @param raw
     * @return
     */
    public static String formatTime(String raw) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        Date dateTime = null;
        try {
            dateTime = sdf1.parse(raw);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("H:mm");
        String format = sdf2.format(dateTime);
        //将时间改为东八区时间，即将小时数+8
        String[] split = format.split(":");
        int hour = (Integer.parseInt(split[0]) + 8) % 24;
        String time =hour/10>0?hour+":"+split[1]:"0"+hour+":"+split[1];
        return time;
    }
}
