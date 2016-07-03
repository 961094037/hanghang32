package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/18.
 */
public class Var_Id {
    private static  String id;
    private static  int length;
    private static  int length2;
    private static  String friend_id;
    private static  String music_id;
    private static  boolean  thread=true;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Var_Id.id = id;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        Var_Id.length = length;
    }

    public static int getLength2() {
        return length2;
    }

    public static void setLength2(int length2) {
        Var_Id.length2 = length2;
    }

    public static String getFriend_id() {
        return friend_id;
    }

    public static void setFriend_id(String friend_id) {
        Var_Id.friend_id = friend_id;
    }

    public static boolean isThread() {
        return thread;
    }

    public static void setThread(boolean thread) {
        Var_Id.thread = thread;
    }

    public static String getMusic_id() {
        return music_id;
    }

    public static void setMusic_id(String music_id) {
        Var_Id.music_id = music_id;
    }
}
