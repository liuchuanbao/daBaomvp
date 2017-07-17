package cn.efarm360.com.dabaomvp.activity.VideoPlayView;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.activity.VideoPlayView.interfaces.OnBooleanListener;
import cn.efarm360.com.dabaomvp.activity.VideoPlayView.serverce.CameraLiveWallpaper;
import cn.efarm360.com.dabaomvp.activity.VideoPlayView.serverce.VideoLiveWallpaper;

public class VideoLiveActivity extends baseActivty implements OnBooleanListener {

    private CheckBox mCbVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_live);

        mCbVoice = (CheckBox) findViewById(R.id.id_cb_voice);

        permissionRequests(Manifest.permission.CAMERA,this,this);

        mCbVoice.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            // 静音
                            VideoLiveWallpaper.voiceSilence(getApplicationContext());
                        } else {
                            VideoLiveWallpaper.voiceNormal(getApplicationContext());
                        }
                    }
                });
    }
    //设置视频壁纸
    public void setVideoToWallPaper(View view) {
        VideoLiveWallpaper.setToWallPaper(this);
    }

    //设置透明壁纸
    public void  setTransprentToWallPaper(){
        CameraLiveWallpaper.setToWallPapers(this);
    }

    @Override
    public void onClick(boolean bln) {
        //简单的设置壁纸
//        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
//        Intent chooser = Intent.createChooser(pickWallpaper,"设置壁纸");
//        startActivity(chooser);
        startWallpaper();
//        setTransparentWallpaper();
    }

    void startWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper, "设置壁纸"    );
        startActivity(chooser);

    }

    /**
     * 不需要手动启动服务
     */
    void setTransparentWallpaper() {
        startService(new Intent(this, CameraLiveWallpaper.class));
    }


}
