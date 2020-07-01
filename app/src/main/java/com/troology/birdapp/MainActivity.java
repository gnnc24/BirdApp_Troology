package com.troology.birdapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.troology.birdapp.Adapters.TabViewPagerAdapter;
import com.troology.birdapp.Api.RequestApi;
import com.troology.birdapp.Common.MyBaseActivity;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.Fragments.AddTextFragment;
import com.troology.birdapp.Fragments.PreviewFragment;
import com.troology.birdapp.Fragments.SelectThemeFragment;
import com.troology.birdapp.Fragments.TakePhotoFragment;
import com.troology.birdapp.Model.LoginModel;
import com.troology.birdapp.WebService.RetrieveStream;
import com.troology.birdapp.WebService.URL;
import com.troology.birdapp.util.CameraUtils;

import android.app.ActionBar;
import android.app.ActionBar.Tab;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends MyBaseActivity implements ActionBar.TabListener, AsyncTaskRequestAPI {
    Button btnLogin, btn_Camera;
    RelativeLayout relativeLogin;
    LinearLayout linearHeader, relativeTab;
    TextView btnskip, text_PostCard;
    ProgressDialog progressDialog;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int tabCount = 0;
    private TabViewPagerAdapter adapter;
    EditText user_Name, user_Location;
    ImageView imagelogo;
    // Activity request codes
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLogin = findViewById(R.id.relativeLogin);
        // linearHeader = findViewById(R.id.LinearHeader);
        btnLogin = findViewById(R.id.emp_btnLogin);
        btnskip = findViewById(R.id.btn_skip);
        relativeTab = findViewById(R.id.relativeTab);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        user_Name = findViewById(R.id.edt_userName);
        user_Location = findViewById(R.id.edt_userLocation);
        text_PostCard = findViewById(R.id.text_PostCard);
        btn_Camera = findViewById(R.id.btn_camera);
        imagelogo = findViewById(R.id.imagelogo);
        actionBar = getActionBar();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        setUpTabViewPager(viewPager);

        if (getIntent() != null) {
            tabCount = getIntent().getIntExtra("TabCount", 0);
        }
        viewPager.setCurrentItem(tabCount);


        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
            finish();
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
              //  actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagelogo.setVisibility(View.GONE);
                relativeLogin.setVisibility(View.GONE);
                relativeTab.setVisibility(View.VISIBLE);
                text_PostCard.setVisibility(View.VISIBLE);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_Name.getText().toString().isEmpty()) {
                    user_Name.setError("Required Event Name");
                } else if (user_Location.getText().toString().isEmpty()) {
                    user_Location.setError("Required Phone No");
                } else {
                    LoginModel loginModel = new LoginModel();
                    loginModel.setName(user_Name.getText().toString());
                    loginModel.setCity(user_Location.getText().toString());
                    loginModel.setAppName("postcard");
                    Gson gson = new Gson();
                    String jsonData = gson.toJson(loginModel);
                    if (jsonData != null) {
                        RequestApi.RequestLoginPost(MainActivity.this, jsonData);
                    }

                    imagelogo.setVisibility(View.GONE);
                    relativeLogin.setVisibility(View.GONE);
                    relativeTab.setVisibility(View.VISIBLE);
                    text_PostCard.setVisibility(View.VISIBLE);

                    Toast.makeText(MainActivity.this, "Submited", Toast.LENGTH_SHORT).show();


                }
            }
        });


    }


    private void setUpTabViewPager(ViewPager viewPager) {
        adapter = new TabViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new TakePhotoFragment(), "Take Photo");
        adapter.addFragment(new SelectThemeFragment(), "SelectTheme");
        adapter.addFragment(new AddTextFragment(), "Add Text");
        adapter.addFragment(new PreviewFragment(), "Preview");


        viewPager.setAdapter(adapter);
    }


    public void selectPage(int i) {
        viewPager.setCurrentItem(i);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.clearOnTabSelectedListeners();
        for (View v : tabLayout.getTouchables()) {
            v.setEnabled(false);
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }






}
