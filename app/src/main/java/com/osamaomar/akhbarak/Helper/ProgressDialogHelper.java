package com.osamaomar.akhbarak.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.osamaomar.akhbarak.R;


public class ProgressDialogHelper {
    private static ProgressDialog mProgressDialog;
    public static void showSimpleProgressDialog(Context context,boolean isCancelable) {

        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
              //  mProgressDialog.setIndeterminateDrawable();
                mProgressDialog.show();
                // mProgressDialog = ProgressDialog.show(context, title, msg);

                mProgressDialog.setContentView(R.layout.progress_dialog_layout);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                mProgressDialog.setCancelable(isCancelable);
                ProgressBar bar = mProgressDialog.findViewById(R.id.progressBar1);
                Drawable progressDrawable = bar.getIndeterminateDrawable().mutate();
                progressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                bar.setProgressDrawable(progressDrawable);
//                TextView textView = (TextView) mProgressDialog.findViewById(R.id.texts);
//                textView.setText(text);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSimpleProgressDialog(Context context, String text
            , boolean isCancelable) {
        try {

            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.show();
                // mProgressDialog = ProgressDialog.show(context, title, msg);

                mProgressDialog.setContentView(R.layout.progress_dialog_layout);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                mProgressDialog.setCancelable(isCancelable);
                ProgressBar bar = mProgressDialog.findViewById(R.id.progressBar1);
                Drawable progressDrawable = bar.getIndeterminateDrawable().mutate();
                progressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                bar.setProgressDrawable(progressDrawable);
                TextView textView = (TextView) mProgressDialog.findViewById(R.id.texts);
                textView.setText(text);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
