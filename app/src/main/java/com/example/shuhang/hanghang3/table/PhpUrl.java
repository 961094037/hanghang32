package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/18.
 */
public class PhpUrl {
    private static final String baseIP="http://115.159.155.68:81/php/";
    private static final String MUSIC_LIST=baseIP+"community_list.php";
    private static final String COMMENT_LIST= baseIP+"comment_list.php";
    private static final String SENT_COMMENT = baseIP+"comment.php";
    private static final String UPLOAD_MUSIC =baseIP+"upload_music.php";
    private static final String LOGIN = baseIP+"login.php";
    private static final String UPLOAD_HEAD= baseIP+"ImgUpload.php";
    private static final String REGISTER=baseIP+"register.php";
    private static final String ZAN=baseIP+"zan.php";
    private static final String FLOWER=baseIP+"flower.php";
    private static final String GET_FLOWER=baseIP+"get_flower.php";
    private static final String RANK=baseIP+"rank.php";
    private static final String SPACE=baseIP+"space.php";
    private static final String MUSIC_DATA=baseIP+"music_data.php";
    private static final String UPSPACE=baseIP+"upspace.php";
    private static final String UPSIGN=baseIP+"upsign.php";
    private static final String FRIEND=baseIP+"friend.php";
    private static final String ADD=baseIP+"add.php";
    private static final String FRIEND_REQUEST= baseIP+"friend_request.php";
    private static final String REQUESTDATA=baseIP+"requestdata.php";
    private static final String REMOVE_REFUSE=baseIP+"remove_refuse.php";
    private static final String ACCEPT=baseIP+"accept.php";
    private static final String DELETE=baseIP+"delete.php";

    public static String getCommentList() {
        return COMMENT_LIST;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getMusicList() {
        return MUSIC_LIST;
    }

    public static String getSentComment() {
        return SENT_COMMENT;
    }

    public static String getUploadHead() {
        return UPLOAD_HEAD;
    }

    public static String getUploadMusic() {
        return UPLOAD_MUSIC;
    }

    public static String getREGISTER() {
        return REGISTER;
    }

    public static String getZAN() {
        return ZAN;
    }

    public static String getFLOWER() {
        return FLOWER;
    }

    public static String getGetFlower() {
        return GET_FLOWER;
    }

    public static String getRANK() {
        return RANK;
    }

    public static String getSPACE() {
        return SPACE;
    }

    public static String getMusicData() {
        return MUSIC_DATA;
    }

    public static String getUPSPACE() {
        return UPSPACE;
    }

    public static String getUPSIGN() {
        return UPSIGN;
    }

    public static String getFRIEND() {
        return FRIEND;
    }

    public static String getADD() {
        return ADD;
    }

    public static String getFriendRequest() {
        return FRIEND_REQUEST;
    }

    public static String getREQUESTDATA() {
        return REQUESTDATA;
    }

    public static String getRemoveRefuse() {
        return REMOVE_REFUSE;
    }

    public static String getACCEPT() {
        return ACCEPT;
    }

    public static String getDELETE() {
        return DELETE;
    }
}
