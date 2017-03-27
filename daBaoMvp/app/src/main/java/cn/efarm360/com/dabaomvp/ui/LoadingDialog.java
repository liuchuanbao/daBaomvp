package cn.efarm360.com.dabaomvp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.efarm360.com.dabaomvp.R;

/**
 * 弹出一个进度提示框。
 */

public class LoadingDialog extends Dialog {

    private TextView tv;

    public LoadingDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progressbar);
        tv = (TextView)findViewById(R.id.tv);
        tv.setText("正在控制.....");
        LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }

}
