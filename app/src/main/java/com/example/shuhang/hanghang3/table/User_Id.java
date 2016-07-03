package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/18.
 */
public class User_Id {
    private static  String id;
    private static  int length;
    private static  int length2;
    private static  String friend_id;
    private static  boolean  thread=true;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        User_Id.id = id;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        User_Id.length = length;
    }

    public static int getLength2() {
        return length2;
    }

    public static void setLength2(int length2) {
        User_Id.length2 = length2;
    }

    public static String getFriend_id() {
        return friend_id;
    }

    public static void setFriend_id(String friend_id) {
        User_Id.friend_id = friend_id;
    }

    public static boolean isThread() {
        return thread;
    }

    public static void setThread(boolean thread) {
        User_Id.thread = thread;
    }
}
