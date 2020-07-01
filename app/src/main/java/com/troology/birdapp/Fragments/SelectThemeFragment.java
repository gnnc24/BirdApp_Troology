package com.troology.birdapp.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.troology.birdapp.Adapters.ThemeAdapter;
import com.troology.birdapp.Api.RequestApi;
import com.troology.birdapp.Common.BaseFragment;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.MainActivity;
import com.troology.birdapp.Model.ConnectivityReceiver;
import com.troology.birdapp.Model.PCThemeListModel;
import com.troology.birdapp.Model.PostcardThemeModel;
import com.troology.birdapp.R;
import com.troology.birdapp.WebService.RetrieveStream;
import com.troology.birdapp.util.AlertDialogManager;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectThemeFragment extends BaseFragment {
    public AlertDialogManager alert = new AlertDialogManager();
    private static final String TAG = "SecondFragment";
    public boolean isDataLoaded = false;
    ProgressDialog progressDialog;
    GridView recyclerView;
    ThemeAdapter gridViewAdapters;
    FrameLayout select_fragment;
    RelativeLayout relativeLayout;
  /*  SendMessage SM;*/
    FrameLayout frameLayout;
    private static int TIME_OUT = 4000; //
    public SelectThemeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_theme, container, false);

        select_fragment = v.findViewById(R.id.select_fragment);
        relativeLayout = v.findViewById(R.id.relative1);
        if (checkConnection()) {


            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading..."); // Setting Message
            //  progressDialog.setTitle("Fetch Data"); // Setting Title
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
            progressDialog.show(); // Display Progress Dialog
            progressDialog.setCancelable(false);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }
            }).start();
            RequestApi.RequestPostcardThemeApi(this);

        }
        recyclerView = v.findViewById(R.id.recycler_theme);

        return v;
    }

    public void setPCThemeData(PostcardThemeModel postcardThemeModel) {
        try {


            if (postcardThemeModel != null) {
                List<PCThemeListModel> data = new ArrayList<>();
                isDataLoaded = true;
                for (PCThemeListModel datumAdditional : postcardThemeModel.getData()) {
                    data.add(datumAdditional);
                }
                gridViewAdapters = new ThemeAdapter(getActivity(), data);
                recyclerView.setAdapter(gridViewAdapters);
                recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PCThemeListModel pcThemeListModel = (PCThemeListModel) parent.getItemAtPosition(position);
                        SharedPreferencesHelper.setPCThemeListModel(pcThemeListModel);
                        ((MainActivity) getActivity()).selectPage(2);

                    }


                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
