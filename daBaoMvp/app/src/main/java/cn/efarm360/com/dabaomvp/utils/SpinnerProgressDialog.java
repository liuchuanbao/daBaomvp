package cn.efarm360.com.dabaomvp.utils;

import android.app.ProgressDialog;
import android.content.Context;

import org.xmlpull.v1.XmlPullParser;

public class SpinnerProgressDialog {
    private Context context;
    private ProgressDialog pd;

    public SpinnerProgressDialog(Context context) {
        this.context = context;
    }

    public void showProgressDialog(String msg) {
        try {
            if (this.pd == null) {
                this.pd = new ProgressDialog(this.context);
                this.pd.setProgressStyle(0);
            }
            this.pd.setMessage(msg);
            if (!this.pd.isShowing()) {
                this.pd.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        if (!this.pd.isShowing()) {
            this.pd.show();
        }
    }

    public ProgressDialog getPd() {
        return this.pd;
    }

    public void cancelProgressDialog(String msg) {
        try {
            if (this.pd != null && this.pd.isShowing()) {
                this.pd.cancel();
            }
            if (msg != null) {
                msg.equals(XmlPullParser.NO_NAMESPACE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
