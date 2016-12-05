package com.buaa.study2.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zgq on 2016/12/5.
 */
public final class StringUtil {
    public static boolean isEmpty(String str){
        if (str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
