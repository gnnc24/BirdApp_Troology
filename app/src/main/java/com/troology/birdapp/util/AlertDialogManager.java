package com.troology.birdapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.troology.birdapp.Common.BaseFragment;
import com.troology.birdapp.Common.MyBaseActivity;
import com.troology.birdapp.R;
import com.troology.birdapp.WebService.DialogServices;


/**
 * Created by jaimatadi on 22-Apr-16.
 */
public class AlertDialogManager {

    /**
     *
     *
     *
     *
     * Function to display simple Alert Dialog
     * @param context - com.example.com.spysr.travel.flight.application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */

    static DialogServices dialogServices;
    private Context mContext;

    // Withot Click Event
    public void showAlertNormalDialog(Context context, BaseFragment fragment, String title, String message,
                                      Boolean status) {

        if (fragment==null){
            mContext = context;
        }else{
            mContext = fragment.getContext();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

// finish the activity
    public void showAlertDialog(final Context context, String title, String message,
                                Boolean status) {
        dialogServices = (DialogServices)context;
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.tick : R.drawable.delete);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogServices.onDialogalertButtonClick(context, dialog);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
// Change the activity
    public void showAlertDialogClick(final Context context, String title, String message) {
        dialogServices = (DialogServices)context;
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialogServices.onDialogButtonClick(context, dialog);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

// Message with limited time
    public void showAlertDialogMsg(final Context context, String title, String message,
                                   Boolean status) {
        dialogServices = (DialogServices)context;
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        // Setting Dialog Message
        alertDialog.setMessage(message);
        alertDialog.setTitle(title);
        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.smily_icon : R.drawable.sad_icon);
            Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(200);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    dialogServices.onDialogMsgButtonClick(context);

                }
            }
        };
        timerThread.start();

        // Showing Alert Message
        alertDialog.show();
    }

  public void showToast( BaseFragment fragment, String msg, Boolean status) {
      Toast customtoast=new Toast(mContext);
      LayoutInflater inflater=fragment.getLayoutInflater();
      View customToastroot =inflater.inflate(R.layout.red_toast, null);
      RelativeLayout rel_toast=(RelativeLayout)customToastroot.findViewById(R.id.rel_toast);
      ImageView status_icon=(ImageView)customToastroot.findViewById(R.id.status_icon);
      TextView error_msg=(TextView)customToastroot.findViewById(R.id.error_msg);
      error_msg.setText(msg);
      if(status != null)
          status_icon.setImageResource((status) ? R.drawable.tick : R.drawable.delete);
      rel_toast.setBackgroundResource((status) ? R.drawable.editext_border : R.drawable.error_custom_toast);
      customtoast.setView(customToastroot);
      customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
      customtoast.setDuration(Toast.LENGTH_SHORT);
      customtoast.show();
  }
}
