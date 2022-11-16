package com.zf.bk2.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UrlUtils {

    /**
     * 向url链接追加参数
     *
     * @param url
     * @param params Map<String, String>
     * @return
     */
    public static String appendParams(String url, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            return "";
        } else if (null == params || 0 == params.size()) {
            return url.trim();
        } else {
            StringBuffer sb = new StringBuffer("");
            Set<String> keys = params.keySet();
            String value = null;
            for (String key : keys) {
                value = params.get(key);
                if (StringUtils.isBlank(value)) value = "";
                sb.append(key).append("=").append(value).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);

            url = url.trim();
            int length = url.length();
            int index = url.indexOf("?");
            if (index > -1) {//url说明有问号
                if ((length - 1) == index) {//url最后一个符号为？，如：http://wwww.baidu.com?
                    url += sb.toString();
                } else {//情况为：http://wwww.baidu.com?aa=11
                    url += "&" + sb.toString();
                }
            } else {//url后面没有问号，如：http://wwww.baidu.com
                url += "?" + sb.toString();
            }
            return url;
        }
    }

    /**
     * 向url链接追加参数(单个)
     *
     * @param url
     * @param name  String
     * @param value String
     * @return
     */
    public static String appendParam(String url, String name, String value) {
        if (StringUtils.isBlank(url)) {
            return "";
        } else if (StringUtils.isBlank(name)) {
            return url.trim();
        } else {
            Map<String, String> params = new HashMap<String, String>();
            params.put(name, value);
            return appendParams(url, params);
        }
    }

    /**
     * 移除url链接的多个参数
     *
     * @param url        String
     * @param paramNames String[]
     * @return
     */
    public static String removeParams(String url, String... paramNames) {
        if (StringUtils.isBlank(url)) {
            return "";
        } else if (null == paramNames || 0 == paramNames.length) {
            return url.trim();
        } else {
            url = url.trim();
            int length = url.length();
            int index = url.indexOf("?");
            if (index > -1) {//url说明有问号
                if ((length - 1) == index) {//url最后一个符号为？，如：http://wwww.baidu.com?
                    return url;
                } else {//情况为：http://wwww.baidu.com?aa=11或http://wwww.baidu.com?aa=或http://wwww.baidu.com?aa
                    String baseUrl = url.substring(0, index);
                    String paramsString = url.substring(index + 1);
                    String[] params = paramsString.split("&");
                    // if (!StringUtils.isEmptyArray(params)) {
                    if (null != params && 0 != params.length) {
                        Map<String, String> paramsMap = new HashMap<String, String>();
                        for (String param : params) {
                            if (!StringUtils.isBlank(param)) {
                                String[] oneParam = param.split("=");
                                String paramName = oneParam[0];
                                int count = 0;
                                for (int i = 0; i < paramNames.length; i++) {
                                    if (paramNames[i].equals(paramName)) {
                                        break;
                                    }
                                    count++;
                                }
                                if (count == paramNames.length) {
                                    paramsMap.put(paramName, (oneParam.length > 1) ? oneParam[1] : "");
                                }
                            }
                        }
                        // if (!StringUtils.isEmptyMap(paramsMap)) {
                        if (null != paramsMap && 0 != paramsMap.size()) {
                            StringBuffer paramBuffer = new StringBuffer(baseUrl);
                            paramBuffer.append("?");
                            Set<String> set = paramsMap.keySet();
                            for (String paramName : set) {
                                paramBuffer.append(paramName).append("=").append(paramsMap.get(paramName)).append("&");
                            }
                            paramBuffer.deleteCharAt(paramBuffer.length() - 1);
                            return paramBuffer.toString();
                        }
                        return baseUrl;
                    }
                }
            }
            return url;
        }
    }

    public static void main(String[] args) {
        /**/
        String a = "http://wwww.baidu.com";
        String b = "http://wwww.baidu.com?";
        String c = "http://wwww.baidu.com?aa=11";
        System.out.println("a=" + appendParam(a, "bb", "1"));
        System.out.println("a=" + appendParam(a, "bb", "2"));

        // String d = "http://wwww.baidu.com?aa";
        // String e = "http://wwww.baidu.com?aa=11&bb=22&cc=33";
        // String f = "http://wwww.baidu.com?aa=11&bb=22&cc=33&dd=";
        // String g = "http://wwww.baidu.com?aa=11&bb=22&cc=33&dd";
        // System.out.println("g=" + removeParams(g, "cc", "aa"));
    }
}