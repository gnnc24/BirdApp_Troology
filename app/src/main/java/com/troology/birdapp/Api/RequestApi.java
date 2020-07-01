package com.troology.birdapp.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.Fragments.AddTextFragment;
import com.troology.birdapp.Fragments.PreviewFragment;
import com.troology.birdapp.Fragments.SelectThemeFragment;
import com.troology.birdapp.MainActivity;
import com.troology.birdapp.Model.LoginModel;
import com.troology.birdapp.Model.PostcardAddTextModel;
import com.troology.birdapp.Model.PostcardThemeModel;
import com.troology.birdapp.Model.PreViewModel;
import com.troology.birdapp.Services.RequestService;
import com.troology.birdapp.WebService.URL;
import java.lang.reflect.Type;
import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
import static com.troology.birdapp.util.Constant.AddText_REQUEST_CODE;
import static com.troology.birdapp.util.Constant.LoginPost_REQUEST_CODE;
import static com.troology.birdapp.util.Constant.PostCardPost_REQUEST_CODE;
import static com.troology.birdapp.util.Constant.ThemeGet_REQUEST_CODE;

public class RequestApi {
    //makeReservationPostMethod
    public static void RequestLoginPost(final MainActivity activity, String jsonData) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        final Gson gson = gsonBuilder.create();

        //GetTourType
        new AsyncTaskRequestAPI.FetchDataPOSTActivity(activity, URL.RequestUrl.UserLoginApi, LoginPost_REQUEST_CODE, jsonData).setListener(new RequestService() {


            @Override
            public void getDataResponse(String corporateRateResult, int RequestCode) {

                try {
                    if (corporateRateResult != null) {
                        Type type = new TypeToken<LoginModel>() {
                        }.getType();
                        LoginModel corporateRate = new GsonBuilder().create().fromJson(corporateRateResult, type);
                        if (corporateRate != null) {
                            //activity.getMakeReservation(makeReservation);
                            //SharedPreferencesHelper.setAdditionalParkingService(parkingAdditionalServices);
                            // SharedPreferencesHelper.setDatum(datum);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, LoginPost_REQUEST_CODE).executeOnExecutor(THREAD_POOL_EXECUTOR);



    }

    public static void RequestPostCardPost(final PreviewFragment fragment, String jsonData) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        final Gson gson = gsonBuilder.create();

        //GetTourType
        new AsyncTaskRequestAPI.FetchDataPOST(fragment, URL.RequestUrl.PostCardPostApi, PostCardPost_REQUEST_CODE, jsonData).setListener(new RequestService() {


            @Override
            public void getDataResponse(String corporateRateResult, int RequestCode) {

                try {
                    if (corporateRateResult != null) {
                        Type type = new TypeToken<PreViewModel>() {
                        }.getType();
                        PreViewModel corporateRate = new GsonBuilder().create().fromJson(corporateRateResult, type);
                        if (corporateRate != null) {
                            //activity.getMakeReservation(makeReservation);
                            //SharedPreferencesHelper.setAdditionalParkingService(parkingAdditionalServices);
                            // SharedPreferencesHelper.setDatum(datum);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, PostCardPost_REQUEST_CODE).executeOnExecutor(THREAD_POOL_EXECUTOR);



    }

    //GetPostcardThemeApi
    public static void RequestPostcardThemeApi(final SelectThemeFragment fragment) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        final Gson gson = gsonBuilder.create();

        //AdditionalServicesUrl
        new AsyncTaskRequestAPI.FetchDataGET(fragment, URL.RequestUrl.Postcard_ThemeApi, ThemeGet_REQUEST_CODE).setListener(new RequestService() {

            @Override
            public void getDataResponse(String additionalsrviceResult, int RequestCode) {

                try {
                    if (additionalsrviceResult != null) {
                        Type type = new TypeToken<PostcardThemeModel>() {
                        }.getType();
                        PostcardThemeModel postcardThemeModel = new GsonBuilder().create().fromJson(additionalsrviceResult, type);
                        if (postcardThemeModel != null) {
                            fragment.setPCThemeData(postcardThemeModel);
                            SharedPreferencesHelper.setPostcardThemeModel(postcardThemeModel);
                            // SharedPreferencesHelper.setDatum(datum);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, ThemeGet_REQUEST_CODE).executeOnExecutor(THREAD_POOL_EXECUTOR);
    }
    //GetImageName
    public static void RequestFromServerGetStates(final AddTextFragment fragment) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        final Gson gson = gsonBuilder.create();
        new AsyncTaskRequestAPI.FetchDataGET(fragment, URL.RequestUrl.GetAddTextUrl, AddText_REQUEST_CODE).setListener(new RequestService() {

            @Override
            public void getDataResponse(String getStatesResult, int RequestCode) {

                try {
                    if (getStatesResult != null) {
                        Type type = new TypeToken<PostcardAddTextModel>() {
                        }.getType();
                        PostcardAddTextModel getPCAddtextModel = new GsonBuilder().create().fromJson(getStatesResult, type);
                        if (getPCAddtextModel != null) {
                            fragment.setTextAtImg(getPCAddtextModel);
                            // SharedPreferencesHelper.setAdditionalParkingService(parkingAdditionalServices);
                            // SharedPreferencesHelper.setDatum(datum);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, AddText_REQUEST_CODE).executeOnExecutor(THREAD_POOL_EXECUTOR);
    }

}
