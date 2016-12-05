package com.buaa.study2.utils;

import com.mysql.cj.core.util.StringUtils;

/**
 * Created by zgq on 2016/12/3.
 * g各种类型转换
 */
public final class CastUtil {
    public static String castString(Object obj){
        return CastUtil.castString(obj,"");
    }
    public static String castString(Object obj,String defaultValue){
        return obj!=null?String .valueOf(obj):defaultValue;
    }

    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0);
    }
    public static double castDouble(Object obj,double defaultValue){
        double doubleVale=defaultValue;
        if(obj!=null){
            String strValue=castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    doubleVale=Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleVale=defaultValue;
                }
            }

        }
        return doubleVale;
    }

    public static long castLong(Object obj){
        return CastUtil.castLong(obj,0);
    }
    public static Long castLong(Object obj,long defaultValue){
        long longVale=defaultValue;
        if(obj!=null){
            String strValue=castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    longVale=Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longVale=defaultValue;
                }
            }

        }
        return longVale;
    }

    public static int castInt(Object obj){
        return CastUtil.castInt(obj,0);
    }
    public static int castInt(Object obj,int defaultValue){
        int intValue=defaultValue;
        if(obj!=null){
            String strValue=castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    intValue=Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue=defaultValue;
                }

            }
        }
        return intValue;
    }

    public static Boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj,false);
    }
    public static Boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue=defaultValue;
        if(obj!=null){
            booleanValue=Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
