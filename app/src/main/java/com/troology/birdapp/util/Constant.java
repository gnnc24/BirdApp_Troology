package com.troology.birdapp.util;

import android.os.Environment;

import com.troology.birdapp.Model.User;

import java.io.File;

public class Constant {
    public static int LoginPost_REQUEST_CODE = 1;
    public static int ThemeGet_REQUEST_CODE = 2;
    public static int AddText_REQUEST_CODE = 3;
    public static int PostCardPost_REQUEST_CODE = 4;
    public static String taskDetal;
    public static String AssignParentTaskList="AssignParentTaskList";

    public interface SharedPref {
        String Theme_ItemModel = "Theme_ItemModel";
        String LoginModel = "LoginModel";
        String postcardThemeModel = "postcardThemeModel";
        String PCThemeListModel = "PCThemeListModel";
        String PostcardAddTextModel ="PostcardAddTextModel";
        String PhotoModel = "PhotoModel";
        String User = "User";
        String CardPostModel = "CardPostModel";
    }
    public static File db_Files_Dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" +"com.example.asus.taskmanagement"  + "/dbfile");
}
