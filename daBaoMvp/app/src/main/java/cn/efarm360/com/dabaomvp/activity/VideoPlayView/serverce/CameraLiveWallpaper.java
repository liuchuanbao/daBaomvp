package cn.efarm360.com.dabaomvp.activity.VideoPlayView.serverce;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by liumeng on 2017/5/12.
 */

public class CameraLiveWallpaper extends WallpaperService {


    @Override
    public Engine onCreateEngine() {
//        return new VideoEngine();

        return new CameraEngine();
    }
    //设置视频壁纸
    public static void setToWallPapers(Context context) {
        final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                new ComponentName(context, CameraLiveWallpaper.class));
        context.startActivity(intent);
    }

    class CameraEngine extends Engine implements Camera.PreviewCallback {
        private Camera camera;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            startPreview();
            // 设置处理触摸事件
            setTouchEventsEnabled(true);

        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            // 时间处理:点击拍照,长按拍照
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            stopPreview();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if (visible) {
                startPreview();
            } else {
                stopPreview();
            }
        }

        /**
         * 开始预览
         */
        public void startPreview() {
            camera = Camera.open();
            camera.setDisplayOrientation(90);

            try {
                camera.setPreviewDisplay(getSurfaceHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();

        }

        /**
         * 停止预览
         */
        public void stopPreview() {
            if (camera != null) {
                try {
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    // camera.lock();
                    camera.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                camera = null;
            }
        }

        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
            camera.addCallbackBuffer(bytes);
        }
    }



    /**
     * 播放视频
     */
    class VideoEngine extends Engine{

        private MediaPlayer mediaPlayer ;
        private String videoPath ;

        /**
         * 播放
         */
        private void play(SurfaceHolder surfaceHolder,String videoPath){
            mediaPlayer = new MediaPlayer();
            // 设置多媒体流类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            // 设置用于展示mediaPlayer的容器
            mediaPlayer.setSurface(surfaceHolder.getSurface());
//            mediaPlayer.setDisplay(surfaceHolder);//用这个方法会报 Wallpapers do not support keep screen on
            try {
                mediaPlayer.setDataSource(videoPath);
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } catch (Exception e) {
                Log.i("通知", "播放过程中出现了错误哦");
            }
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            setTouchEventsEnabled(true);
//            videoPath = Environment.getExternalStorageDirectory()+ File.separator+"myVideo"+File.separator+"lm.mp4";
            videoPath = Environment.getExternalStorageDirectory()+"12345.mp4";
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    play(holder,videoPath);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                }

            });

        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
        }
    }

}
