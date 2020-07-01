package com.troology.birdapp.Fragments;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.troology.birdapp.Adapters.AddTextItemAdapter;
import com.troology.birdapp.Api.RequestApi;
import com.troology.birdapp.Common.BaseFragment;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.MainActivity;
import com.troology.birdapp.Model.PostcardAddTextModel;
import com.troology.birdapp.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddTextFragment extends BaseFragment {

    ListView recyclerView;
    public static int itemSelected = -1;
    public boolean isDataLoaded = false;
    Button btn_Done;
    ProgressDialog progressDialog;
    public static AddTextItemAdapter textItemAdapter;
    ViewPager viewPagers;
    PostcardAddTextModel textModel;
    ProgressBar mBar;
    public AddTextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_text, container, false);
        // spinner_State = v.findViewById(R.id.spinner_State);

        recyclerView = v.findViewById(R.id.recycler_selectText);
        viewPagers = v.findViewById(R.id.viewPager);

        //spinner_State.setVisibility(View.VISIBLE);
        btn_Done = v.findViewById(R.id.btn_Done);
        final RelativeLayout relativeMain = v.findViewById(R.id.relativeMain);
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
            RequestApi.RequestFromServerGetStates(this);

        }


        btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).selectPage(3);

            }
        });

        return v;
    }


    public void setTextAtImg(PostcardAddTextModel getPCAddtextModel) {
        try {
            if (getPCAddtextModel != null) {
                //spinner_State.setVisibility(View.VISIBLE);
                List<String> data = new ArrayList<>();
                isDataLoaded = true;
                for (String postcardAddTextModelList : getPCAddtextModel.getData()) {
                    data.add(postcardAddTextModelList);


                }

                final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                recyclerView.setAdapter(dataAdapter);

                recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String SelectedItem = (String) parent.getItemAtPosition(position);
                        Toast.makeText(getActivity(), "" + SelectedItem, Toast.LENGTH_SHORT).show();
                        PostcardAddTextModel textModel = new PostcardAddTextModel();
                        textModel.setData(Collections.singletonList(SelectedItem));
                        textModel.getData().toString().replace("[", "").replace("]", "");
                        SharedPreferencesHelper.setPostcardAddTextModel(textModel);
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
