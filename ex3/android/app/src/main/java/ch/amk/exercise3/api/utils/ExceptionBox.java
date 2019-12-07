package ch.amk.exercise3.api.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

public class ExceptionBox {

    public final static String TITLE = "Exception Occured";

    private Throwable exception;

    public ExceptionBox(@NonNull Throwable exception) {
        this.exception = exception;
    }

    public void show(@NonNull Activity activity) {
        this.exception.printStackTrace();

        activity.runOnUiThread(() -> {
            //PopupWindow popup = new PopupWindow(activity);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
            alertDialogBuilder
                    .setTitle(TITLE)
                    .setMessage(this.exception.toString());

            alertDialogBuilder
                    .create()
                    .show();
        });
    }

}
