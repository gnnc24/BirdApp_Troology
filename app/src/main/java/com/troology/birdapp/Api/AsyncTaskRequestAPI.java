package com.troology.birdapp.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import androidx.fragment.app.Fragment;

import com.troology.birdapp.Common.BaseFragment;
import com.troology.birdapp.Common.MyBaseActivity;
import com.troology.birdapp.Fragments.AddTextFragment;
import com.troology.birdapp.Fragments.SelectThemeFragment;
import com.troology.birdapp.Services.RequestService;
import com.troology.birdapp.WebService.RetrieveStream;

public class AsyncTaskRequestAPI {
    public static class FetchDataGET extends AsyncTask<String, String, String> {
        Activity mActivity;
        String mUrl;
        RequestService requestTourServices = null;
        int requestCode;
        ProgressDialog progressDialog;
        Fragment fragment;

        public FetchDataGET(MyBaseActivity activity, String url, int RequestCode) {
            this.mActivity = activity;
            this.mUrl = url;
            this.requestCode = RequestCode;
        }
        public FetchDataGET(BaseFragment fragment, String url, int RequestCode) {
            this.fragment = fragment;
            this.mUrl = url;
            this.requestCode = RequestCode;
        }
        public FetchDataGET setListener(RequestService requestServices, int requestCode) {
            this.requestTourServices = requestServices;
            this.requestCode = requestCode;
            return this;
        }
        @Override
        protected void onPreExecute() {
            if(progressDialog !=null)
            {
                progressDialog = null;
            }
            progressDialog = new ProgressDialog(fragment.getActivity());
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Fetching Data From Server......");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            //values["Loading Locations", "Loading Locations", "Loading Locations"];
            return RetrieveStream.retrieveStreamGETWithCustomHeader(mUrl);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(fragment.getActivity() != null){


                if (requestTourServices != null) {
                    //post result with given requestCode
                    progressDialog.dismiss();
                    requestTourServices.getDataResponse(result, requestCode);
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

    }

    public static class FetchDataPOST extends AsyncTask<String, String, String> {

        Activity mActivity;
        String mUrl;
        String jsonData;
        RequestService requestTourServices = null;
        int requestCode;
        ProgressDialog progressDialog;
        Fragment fragment;

        public FetchDataPOST(MyBaseActivity activity, String url, int RequestCode, String jsontring) {
            this.mActivity = activity;
            this.mUrl = url;
            this.requestCode = RequestCode;
            this.jsonData = jsontring;
        }
        public FetchDataPOST(BaseFragment fragment, String url, int RequestCode, String jsontring) {
            this.fragment = fragment;
            this.mUrl = url;
            this.requestCode = RequestCode;
            this.jsonData = jsontring;
        }


        public FetchDataPOST setListener(RequestService requestServices, int requestCode) {
            this.requestTourServices = requestServices;
            this.requestCode = requestCode;
            return this;
        }
        @Override
        protected void onPreExecute() {
            if(progressDialog !=null)
            {
                progressDialog = null;
            }
            progressDialog = new ProgressDialog(fragment.getActivity());
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Fetching Data From Server......");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //values["Loading Locations", "Loading Locations", "Loading Locations"];
            return RetrieveStream.retrieveStreamPOST(mUrl, jsonData);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(requestTourServices!=null){
                //post result with given requestCode
                progressDialog.dismiss();
                requestTourServices.getDataResponse(result, requestCode);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //publishProgress(values);
            super.onProgressUpdate(values);
        }

    }
    public static class FetchDataPOSTActivity extends AsyncTask<String, String, String> {

        Activity mActivity;
        String mUrl;
        String jsonData;
        RequestService requestTourServices = null;
        int requestCode;
        ProgressDialog progressDialog;
        Fragment fragment;

        public FetchDataPOSTActivity(MyBaseActivity activity, String url, int RequestCode, String jsontring) {
            this.mActivity = activity;
            this.mUrl = url;
            this.requestCode = RequestCode;
            this.jsonData = jsontring;
        }
        public FetchDataPOSTActivity(BaseFragment fragment, String url, int RequestCode, String jsontring) {
            this.fragment = fragment;
            this.mUrl = url;
            this.requestCode = RequestCode;
            this.jsonData = jsontring;
        }


        public FetchDataPOSTActivity setListener(RequestService requestServices, int requestCode) {
            this.requestTourServices = requestServices;
            this.requestCode = requestCode;
            return this;
        }
        @Override
        protected void onPreExecute() {
            if(progressDialog !=null)
            {
                progressDialog = null;
            }
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Fetching Data From Server......");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //values["Loading Locations", "Loading Locations", "Loading Locations"];
            return RetrieveStream.retrieveStreamPOST(mUrl, jsonData);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(requestTourServices!=null){
                //post result with given requestCode
                progressDialog.dismiss();
                requestTourServices.getDataResponse(result, requestCode);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //publishProgress(values);
            super.onProgressUpdate(values);
        }

    }
}
