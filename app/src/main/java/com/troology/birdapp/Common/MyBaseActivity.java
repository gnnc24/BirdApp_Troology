package com.troology.birdapp.Common;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.Model.ConnectivityReceiver;
import com.troology.birdapp.util.AlertDialogManager;


public class MyBaseActivity extends AppCompatActivity {

    public SharedPreferences mPrefs;
    public static SharedPreferences.Editor prefsEditor;
    public RelativeLayout leftRL;
    LinearLayout tab_content;
    Fragment currentFragment;
    public AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();
        SharedPreferencesHelper.setmPrefs(mPrefs);
        tab_content = new LinearLayout(this);
        tab_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        tab_content.setScrollContainer(true);
        tab_content.setId(585);
        setContentView(tab_content);

    }
    public void navigateTo(Fragment newFragment, String tag)
    {
        currentFragment = newFragment;

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        // What if i press home  ????????????????

        ft.replace(tab_content.getId(), newFragment,tag);
        ft.addToBackStack(null);

        ft.commit();
    }


    @Override
    public void onBackPressed()
    {
        FragmentManager manager = getFragmentManager();

        if(currentFragment!=null)
        {
            String tag = currentFragment.getTag();
            Log.d("tag", ""+tag);

            if(tag.equalsIgnoreCase("something"))
            {
                // do something
            }
        }

        if (manager.getBackStackEntryCount() > 1)
        {
            super.onBackPressed();
        }
        else
        {
            finish();
        }
    }

    public void clearStack()
    {
        FragmentManager manager = getFragmentManager();

        while(manager.getBackStackEntryCount() > 1)
            manager.popBackStack();

    }

    public Activity getMyActivity(){
        return MyBaseActivity.this;

    }
    @Override
    protected void onStart() {
        super.onStart();

    }

//    public boolean checkConnection() {
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        if (!isConnected){
//            alert.showToast(this, null, "Sorry! Not connected to internet", false);
//        }
//
//
//        return isConnected;
//    }
//
//
//    @Override
//    public void onNetworkConnectionChanged(boolean isConnected) {
//        alert.showToast(this, null, ((isConnected) ? "Connected to Internet" : "Sorry! Not connected to internet"), (isConnected));
//        if (!isDataLoaded){
//            finish();
//            startActivity(getIntent());
//        }
//    }

}
