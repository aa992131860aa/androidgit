package com.daming.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;


/**
 * Created by cy on 2015/8/2.
 */
public class ShareUtils {
    public final static String FILE_NAME = "daming";//存储sharepreference 的xml名称
    public final static String IP = "ip";//ip地址和端口
    public final static String RECORD = "record";//记录号
    public final static String USER_NAME = "userName";//ip地址和端口
    public final static String USER_PWD = "userPwd";//ip地址和端口
    //呼叫记录号
    public final static String HJJLH = "hjjlh";
    public final static String HJJLH1 = "hjjlh1";
    public final static String HJJLH2 = "hjjlh2";
    public final static String HJJLH3 = "hjjlh3";
    public final static String HJJLH4 = "hjjlh4";
    public final static String HJJLH5 = "hjjlh5";
    public final static String HJJLH6 = "hjjlh6";
    public final static String HJJLH7 = "hjjlh7";
    public final static String HJJLH8 = "hjjlh8";

    /**
     * 登陆名
     *
     * @param context
     * @param userName
     */
    public static void setUserName(Context context, String userName) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(USER_NAME, "");
    }
    /**
     * 登录密码保存
     *
     * @param context
     * @param userPwd
     */
    public static void setUserPwd(Context context, String userPwd) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(USER_PWD, userPwd);
        editor.commit();
    }

    public static String getUserPwd(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(USER_PWD, "");
    }
    /**
     * 登录ip保存
     *
     * @param context
     * @param ip
     */
    public static void setIp(Context context, String ip) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(IP, ip);
        editor.commit();
    }

    public static String getIp(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(IP, "");
    }
    /**
     * 记录号存储
     *
     * @param context
     * @param record
     */
    public static void setRecord(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(RECORD, record);
        editor.commit();
    }

    public static String getRecord(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(RECORD, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh1(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH1, record);
        editor.commit();
    }

    public static String getHjjlh1(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH1, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh2(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH2, record);
        editor.commit();
    }

    public static String getHjjlh2(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH2, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh3(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH3, record);
        editor.commit();
    }

    public static String getHjjlh3(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH3, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh4(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH4, record);
        editor.commit();
    }

    public static String getHjjlh4(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH4, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh5(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH5, record);
        editor.commit();
    }

    public static String getHjjlh5(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH5, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh6(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH6, record);
        editor.commit();
    }

    public static String getHjjlh6(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH6, "");
    }  /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh7(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH7, record);
        editor.commit();
    }

    public static String getHjjlh7(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH7, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh8(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH8, record);
        editor.commit();
    }

    public static String getHjjlh8(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH8, "");
    }
    /**
     * 呼叫记录号
     *
     * @param context
     * @param record
     */
    public static void setHjjlh(Context context, String record) {
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(HJJLH, record);
        editor.commit();
    }

    public static String getHjjlh(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(HJJLH, "");
    }

}
