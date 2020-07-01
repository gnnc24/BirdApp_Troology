package com.troology.birdapp.WebService;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by jaimatadi on 29-Sep-16.
 */
public interface DialogServices {

    void onDialogButtonClick(Context context, DialogInterface dialog);

    void onDialogMsgButtonClick(Context context);

    void onDialogalertButtonClick(Context context, DialogInterface dialog);
}
