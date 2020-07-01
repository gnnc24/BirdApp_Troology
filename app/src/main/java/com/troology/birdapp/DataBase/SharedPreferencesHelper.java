package com.troology.birdapp.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.troology.birdapp.Model.CardPostModel;
import com.troology.birdapp.Model.LoginModel;
import com.troology.birdapp.Model.PCThemeListModel;
import com.troology.birdapp.Model.PhotoModel;
import com.troology.birdapp.Model.PostcardAddTextModel;
import com.troology.birdapp.Model.PostcardThemeModel;

import com.troology.birdapp.Model.User;
import com.troology.birdapp.util.Constant;


public class SharedPreferencesHelper {
    public static SharedPreferences mPrefs;
    public static SharedPreferences.Editor prefsEditor;

    public static void setmPrefs(SharedPreferences mPrefs) {
        SharedPreferencesHelper.mPrefs = mPrefs;
        SharedPreferencesHelper.prefsEditor = mPrefs.edit();
    }

    Context context;

    public SharedPreferencesHelper(Context context) {
        this.context = context;
    }


    public static void setLoginModel(LoginModel loginModel) {
        Gson gson = new Gson();
        String register = gson.toJson(loginModel);
        prefsEditor.putString(Constant.SharedPref.LoginModel, register);
        prefsEditor.apply();
    }

    public static LoginModel getLoginModel() {
        Gson gson = new Gson();
        String Registration = mPrefs.getString(Constant.SharedPref.LoginModel, "");
        LoginModel beautyTipsModel = gson.fromJson(Registration, LoginModel.class);
        return beautyTipsModel;
    }
    public static void setPostcardThemeModel(PostcardThemeModel postcardThemeModel) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(postcardThemeModel);
        prefsEditor.putString(Constant.SharedPref.postcardThemeModel, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static PostcardThemeModel getPostcardThemeModel() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.postcardThemeModel, "");
        PostcardThemeModel postcardThemeModel = gson.fromJson(additionalParking, PostcardThemeModel.class);
        return postcardThemeModel;
    }
    public static void setPCThemeListModel(PCThemeListModel postcardThemeModel) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(postcardThemeModel);
        prefsEditor.putString(Constant.SharedPref.PCThemeListModel, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static PCThemeListModel getPCThemeListModel() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.PCThemeListModel, "");
        PCThemeListModel postcardThemeModel = gson.fromJson(additionalParking, PCThemeListModel.class);
        return postcardThemeModel;
    }
    public static void setPostcardAddTextModel(PostcardAddTextModel postcardThemeModel) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(postcardThemeModel);
        prefsEditor.putString(Constant.SharedPref.PostcardAddTextModel, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static PostcardAddTextModel getPostcardAddTextModel() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.PostcardAddTextModel, "");
        PostcardAddTextModel postcardThemeModel = gson.fromJson(additionalParking, PostcardAddTextModel.class);
        return postcardThemeModel;
    }
    public static void setPhotoModel(PhotoModel postcardThemeModel) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(postcardThemeModel);
        prefsEditor.putString(Constant.SharedPref.PhotoModel, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static PhotoModel getPhotoModel() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.PhotoModel, "");
        PhotoModel postcardThemeModel = gson.fromJson(additionalParking, PhotoModel.class);
        return postcardThemeModel;
    }
    public static void setUser(User user) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(user);
        prefsEditor.putString(Constant.SharedPref.User, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static User getUser() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.User, "");
        User postcardThemeModel = gson.fromJson(additionalParking, User.class);
        return postcardThemeModel;
    }
    public static void setCardPostModel(CardPostModel user) {
        Gson gson = new Gson();
        String postcardThemeModels = gson.toJson(user);
        prefsEditor.putString(Constant.SharedPref.CardPostModel, postcardThemeModels);
        //prefsEditor.commit();
        prefsEditor.apply();
    }
    public static CardPostModel getCardPostModel() {
        Gson gson = new Gson();
        String additionalParking = mPrefs.getString(Constant.SharedPref.CardPostModel, "");
        CardPostModel postcardThemeModel = gson.fromJson(additionalParking, CardPostModel.class);
        return postcardThemeModel;


    }

}
