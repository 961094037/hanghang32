package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/23.
 */
public class IconUrl  {
    private static final String baseIP="http://115.159.155.68:81/icon/";
    private static final String ADD=baseIP+"add.png";
    private static final String COMMENT=baseIP+"comment.png";
    private static final String LIKE=baseIP+"like.png";
    private static final String FLOWER=baseIP+"flower.png";
    private static final String PLAY=baseIP+"play.png";
    private static final String PAUSE=baseIP+"pause.png";
    private static final String STOP=baseIP+"stop.png";
    private static final String DELETE=baseIP+"delete.png";

    public static String getADD() {
        return ADD;
    }

    public static String getBaseIP() {
        return baseIP;
    }

    public static String getCOMMENT() {
        return COMMENT;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static String getFLOWER() {
        return FLOWER;
    }

    public static String getLIKE() {
        return LIKE;
    }

    public static String getPAUSE() {
        return PAUSE;
    }

    public static String getPLAY() {
        return PLAY;
    }

    public static String getSTOP() {
        return STOP;
    }
}
