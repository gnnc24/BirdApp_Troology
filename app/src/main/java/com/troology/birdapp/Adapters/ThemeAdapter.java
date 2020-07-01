package com.troology.birdapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.Fragments.SelectThemeFragment;
import com.troology.birdapp.Interfaces.OnClickListener;
import com.troology.birdapp.MainActivity;
import com.troology.birdapp.Model.PCThemeListModel;
import com.troology.birdapp.Model.PostcardThemeModel;

import com.troology.birdapp.R;

import org.w3c.dom.Text;

import java.util.List;

import static com.google.android.gms.wearable.DataMap.TAG;

public class ThemeAdapter extends BaseAdapter {
    Context context;
    List<PCThemeListModel> pcThemeListModels;
    Fragment fragment;
    String baseurl = "http://bird.theme-nulled.in/upload/";

    public ThemeAdapter(Context context, List<PCThemeListModel> theme_itemModelList) {
        this.context = context;
        this.pcThemeListModels = theme_itemModelList;
    }

    @Override
    public int getCount() {
        return pcThemeListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return pcThemeListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.theme_item, null);

        PCThemeListModel subjectModel = pcThemeListModels.get(position);
        // TextView textView = v.findViewById(R.id.im);
        ImageView imageView = v.findViewById(R.id.theme_imgItem);
        TextView textView = v.findViewById(R.id.theme_text);

        //textView.setText(subjectModel.getQuiz_text());
        // PCThemeListModel item = pcThemeListModels.get(i);
        try {
            if (subjectModel.getImage() != null && subjectModel.getName() != null) {

                Uri uri = Uri.parse(baseurl+ subjectModel.getImage());
                Picasso.get().load(uri).into(imageView);
               // String imageName = subjectModel.getImage();
                textView.setText(subjectModel.getName());
                //textView.setText(imageName);


            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

}



