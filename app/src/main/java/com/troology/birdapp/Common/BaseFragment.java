package com.troology.birdapp.Common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;

import android.widget.LinearLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.troology.birdapp.Model.ConnectivityReceiver;
import com.troology.birdapp.util.AlertDialogManager;


public class BaseFragment extends Fragment implements FragmentManager.OnBackStackChangedListener
{
    private boolean removeFragment = false;
   // public RecipeCostingVo recipeCostingVo;
    public int mDownloadInstances = 0;
    protected ProgressDialog mProgressDialog = null;
    public DrawerLayout drawerLayout;
    public Activity activity;
    public boolean isInternetPresent;
    public boolean isNetworkConnectionStatus;
    public AlertDialogManager alert = new AlertDialogManager();
    private LinearLayout tab_content;

    public void removeThisFragment()
    {
        removeFragment = true;
        Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    public void createNewFragment(Fragment newFragment)
    {
        Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(fragment.getId(), newFragment,newFragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @SuppressLint("NewApi")
    public void showExistingFragment(String existingFragmentTagName, boolean clearFragmentTop)
    {

        Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
        Fragment existingFragment = getFragmentManager().findFragmentByTag(existingFragmentTagName);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if(clearFragmentTop)
        {
            int count  = manager.getBackStackEntryCount();
            for(int i=(count-1); i>0; i--)
            {
                FragmentManager.BackStackEntry backStackEntry = manager.getBackStackEntryAt(i);
                int id =  backStackEntry.getId();
                String currentFragmentName =  backStackEntry.getName();
                {
                    manager.popBackStack();
                }
            }
        }
        else
        {
            fragmentTransaction.replace(fragment.getId(),existingFragment);

        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        if(removeFragment)
            getActivity().finish();

    }

    @Override
    public void onBackStackChanged() {
        // TODO Auto-generated method stub
        getActivity().finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        checkConnection();
    }

    public boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected){
            alert.showToast(this,  "Sorry! Not connected to internet", false);
        }


        return isConnected;
    }


}
