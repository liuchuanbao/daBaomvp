package cn.efarm360.com.dabaomvp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by pubinfo on 2017/3/2.
 */

public class ProgressDialogUtils {
          public  static ProgressDialog  getprogressDialo(Context context){
              ProgressDialog progressDialog  = new ProgressDialog(context);
              // 设置进度条风格，风格为圆形，旋转的
              progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
              // 设置ProgressDialog 标题
              progressDialog.setTitle("");
              // 设置ProgressDialog 提示信息
              progressDialog.setMessage("正在加载数据...");
              // 设置ProgressDialog 的进度条是否不明确
              progressDialog.setIndeterminate(false);
              // 设置ProgressDialog 是否可以按退回按键取消
              progressDialog.setCancelable(true);
              progressDialog.setCanceledOnTouchOutside(false);
              return progressDialog;
          }
}
