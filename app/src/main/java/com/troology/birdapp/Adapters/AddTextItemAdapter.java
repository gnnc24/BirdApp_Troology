package com.troology.birdapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.troology.birdapp.DataBase.SharedPreferencesHelper;
import com.troology.birdapp.Fragments.AddTextFragment;
import com.troology.birdapp.Interfaces.OnClickListener;
import com.troology.birdapp.Model.PostcardAddTextModel;
import com.troology.birdapp.R;

import java.util.List;

import static com.troology.birdapp.Fragments.AddTextFragment.itemSelected;

public class AddTextItemAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<PostcardAddTextModel> items;


    public AddTextItemAdapter(Context context, List<PostcardAddTextModel> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_text, parent, false);

        /*view.getLayoutParams().width = (int) (getScreenWidth() / 2); *//// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new AddTextHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        AddTextHolder viewHolder1 = (AddTextHolder) holder;
        viewHolder1.text.setText((CharSequence) items.get(i));
        if(i== itemSelected){
            ((AddTextHolder) holder).text.setBackgroundColor(ContextCompat.getColor(context,R.color.colorOrange));
        } else {
            ((AddTextHolder) holder).text.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        }
       /* viewHolder1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Quiz_Item quizItem =  items.get(i);
                SharedPreferencesHelper.setQuizItem(quizItem);
                Intent quiz_item = new Intent(context, QuizListActivity.class);
                context.startActivity(quiz_item);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
class AddTextHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView text;


    OnClickListener onClickListener;

    public AddTextHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.txt_addText);


        itemView.setOnClickListener(this);//on item click event

    }

    @Override
    public void onClick(View view) {
        onClickListener.onClick(view, getAdapterPosition());
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}