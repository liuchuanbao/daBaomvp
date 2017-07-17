package cn.efarm360.com.dabaomvp.activity.VideoPlayView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import cn.efarm360.com.dabaomvp.activity.VideoPlayView.interfaces.OnBooleanListener;

/**
 * Created by pubinfo on 2017/7/10.
 */

public class baseActivty extends Activity {

    private OnBooleanListener onPermissionListener;
    /**
     * 权限请求
     * @param permission Manifest.permission.CAMERA
     * @param onBooleanListener 权限请求结果回调，true-通过  false-拒绝
     */
    public void permissionRequests(String permission, OnBooleanListener onBooleanListener,Activity activity){
        onPermissionListener=onBooleanListener;
        if (ContextCompat.checkSelfPermission(getApplication(),
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_CONTACTS)) {
                //权限已有
                onPermissionListener.onClick(true);
            } else {
                //没有权限，申请一下
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        1);
            }
        }else {
            //权限已有
            onPermissionListener.onClick(true);
        }
    }

    //在baseActivity里
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限通过
                if(onPermissionListener!=null){
                    onPermissionListener.onClick(true);
                }
            } else {
                //权限拒绝
                if(onPermissionListener!=null){
                    onPermissionListener.onClick(false);
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}
