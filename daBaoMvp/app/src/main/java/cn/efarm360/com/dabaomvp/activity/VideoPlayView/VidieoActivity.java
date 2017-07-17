package cn.efarm360.com.dabaomvp.activity.VideoPlayView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.utils.ToastUtils;

public class VidieoActivity extends AppCompatActivity {

    VideoView myvideoPlay;
    private OnBooleanListener onPermissionListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidieo);
        myvideoPlay = (VideoView) findViewById(R.id.simpleVideoPlay);
        Log.e("xyc", "onCreate: " );

        permissionRequests(Manifest.permission.READ_EXTERNAL_STORAGE, new OnBooleanListener() {
            @Override
            public void onClick(boolean bln) {
                Log.e("xyc", "onCreate: bln =="+bln );
                if(bln){

                    ToastUtils.showLToast(VidieoActivity.this,"权限通过");
                    /**
                     * 本地视频的播放
                     */
//                    String path = Environment.getExternalStorageDirectory().getPath()+"/12345.mp4";
//                    myvideoPlay.setVideoPath(path);
                    /**
                     * 网络视频的播放
                     */
                    String  strURI = "http://122.229.29.32:8081/ltlion/doc/repository/20161116153557cd65.mp4";

                myvideoPlay.setVideoURI(Uri.parse(strURI));
                    //控制视频播放
                    MediaController controller = new MediaController(VidieoActivity.this);
                    //控制与view关联
                    myvideoPlay.setMediaController(controller);
                    controller.setMediaPlayer(myvideoPlay);
                }else{
                    ToastUtils.showLToast(VidieoActivity.this,"权限拒绝");
                }
            }
        });
    }


    /**
     * 权限请求
     * @param permission Manifest.permission.CAMERA
     * @param onBooleanListener 权限请求结果回调，true-通过  false-拒绝
     */
    public void permissionRequests(String permission, OnBooleanListener onBooleanListener){
        onPermissionListener=onBooleanListener;
        if (checkSelfPermission(permission)
                == PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
                //权限已有
                onPermissionListener.onClick(true);
        }else {
            //没有权限，申请一下
            requestPermissions(new String[]{permission},
                    1);
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
    /**
     * 回调错误或者正确
     */
    public interface OnBooleanListener {
        void onClick(boolean bln);
    }
}
