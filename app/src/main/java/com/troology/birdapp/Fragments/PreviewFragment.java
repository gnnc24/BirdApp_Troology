package com.troology.birdapp.Fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.troology.birdapp.Api.RequestApi;
import com.troology.birdapp.Common.BaseFragment;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.MainActivity;
import com.troology.birdapp.Model.PCThemeListModel;
import com.troology.birdapp.Model.PhotoModel;
import com.troology.birdapp.Model.PostcardAddTextModel;
import com.troology.birdapp.Model.PreViewModel;
import com.troology.birdapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreviewFragment extends BaseFragment {

    ImageView imageIntoBackGround, captureImg;
    Button btnSave, btnEdit;
    PCThemeListModel pcThemeListModel;
    PostcardAddTextModel postcardAddTextModel;
    PhotoModel photoModel;
    Bitmap bitmap;
    TextView textPreView;
    ProgressDialog dialog;
    ProgressBar mBar;
    //Gasture Dedector
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector SGD;
    public boolean isDataLoaded = false;
    //Drag and Drop
    int xDelta;
    int yDelta;
    FrameLayout mainLayout;
    RelativeLayout relativeMain;

    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    private long fileSize = 0;
    public PreviewFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_preview, container, false);

        //  mProgressDialog.isShowing();
        final ProgressDialog dialog=new ProgressDialog(getContext());
        dialog.setMessage("message");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (dialog.getProgress() <= dialog
                            .getMax()) {
                        Thread.sleep(4000);
                       //handle.sendMessage(handle.obtainMessage());
                        if (dialog.getProgress() == dialog
                                .getMax()) {
                            dialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        captureImg = v.findViewById(R.id.imageCapture);
        btnEdit = v.findViewById(R.id.btn_Edit);
        btnSave = v.findViewById(R.id.btn_Save);
        //mBar = v.findViewById(R.id.simpleProgressBar);
        textPreView = v.findViewById(R.id.textPreView);
        imageIntoBackGround = v.findViewById(R.id.img_Into_Background);
        relativeMain = v.findViewById(R.id.relativeMain);
        //Drag and Drop
        captureImg.setOnTouchListener(onTouchListener());
        textPreView.setOnTouchListener(onTouchListener());
        mainLayout = v.findViewById(R.id.preViewFragmnet);
        // prepare for a progress bar dialog
        dialog.hide();

        SGD = new ScaleGestureDetector(getContext(), new ScaleListener());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBitmapFromView(relativeMain);
                PreViewModel preViewModel = new PreViewModel();
                String image = pcThemeListModel.getImage();
                preViewModel.setImage(image);
                preViewModel.setImgDetail(textPreView.getText().toString());
                Gson gson = new Gson();
                String jsonData = gson.toJson(preViewModel);
                if (jsonData != null) {
                    RequestApi.RequestPostCardPost(PreviewFragment.this, jsonData);
                    Toast.makeText(getActivity(), "Submited", Toast.LENGTH_SHORT).show();
                }
                ((MainActivity) getActivity()).selectPage(0);
            }
        });
        if (SharedPreferencesHelper.getPCThemeListModel() != null) {
            if (SharedPreferencesHelper.getPostcardAddTextModel() != null) {
                if (SharedPreferencesHelper.getPhotoModel() != null) {
                    try {
                        //Showing Text
                        postcardAddTextModel = SharedPreferencesHelper.getPostcardAddTextModel();
                        String text = (postcardAddTextModel.getData().toString().replace("[", "").replace("]", ""));
                        textPreView.setText(text);
                        //Show captured image
                        photoModel = SharedPreferencesHelper.getPhotoModel();
                        Bitmap bitmapImage = photoModel.getImage();
                        captureImg.setImageBitmap(bitmapImage);
                        photoModel = new PhotoModel();
                        photoModel.setImage(bitmapImage);
                        SharedPreferencesHelper.setPhotoModel(photoModel);
                        //show background image
                        pcThemeListModel = SharedPreferencesHelper.getPCThemeListModel();
                        Uri uri = Uri.parse("http:\\/\\/bird.theme-nulled.in\\/upload\\/" + pcThemeListModel.getImage());
                        Picasso.get().load(uri).into(imageIntoBackGround);
                    } catch (NumberFormatException n) {
                        n.printStackTrace();
                    }
                }
            }
        }
        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    SGD.onTouchEvent(event);
                }
                return true;
            }
        });
        return v;
    }

    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

   /* private void progress() {



        progressBar = new ProgressDialog(getActivity());
        progressBar.setCancelable(true);
        progressBar.setMessage("File downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();

        //reset progress bar status
        progressBarStatus = 0;

        //reset filesize
        fileSize = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = doSomeTasks();

                    // your computer is too fast, sleep 1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                // ok, file is downloaded,
                if (progressBarStatus >= 100) {

                    // sleep 2 seconds, so that you can see the 100%
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // close the progress bar dialog
                    progressBar.dismiss();
                }
            }
        }).start();


    }
*/

    //For Drag and Drop
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x - layoutParams.leftMargin;
                        yDelta = y - layoutParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(getActivity(), "I'm Here", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams1.leftMargin = x - xDelta;
                        layoutParams1.topMargin = y - yDelta;
                        layoutParams1.rightMargin = 0;
                        layoutParams1.bottomMargin = 0;
                        v.setLayoutParams(layoutParams1);
                        break;
                }
                relativeMain.invalidate();
                return true;
            }


        };

    }
    // file download simulator... a really simple
    public int doSomeTasks() {

        while (fileSize <= 1000000) {

            fileSize++;

            if (fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            }
            // ...add your own

        }

        return 100;

    }

    // ZoomIn ZoomOut
    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            captureImg.setImageMatrix(matrix);
            return true;
        }
    }
}
