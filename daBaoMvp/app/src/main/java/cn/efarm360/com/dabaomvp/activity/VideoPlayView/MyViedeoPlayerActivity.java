package cn.efarm360.com.dabaomvp.activity.VideoPlayView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.activity.VideoPlayView.interfaces.OnBooleanListener;
import cn.efarm360.com.dabaomvp.utils.DensityUtils;
import cn.efarm360.com.dabaomvp.utils.ToastUtils;

public class MyViedeoPlayerActivity extends baseActivty implements OnBooleanListener {

    private  VideoView  myVideoView;
     private LinearLayout controllerlinlayout;
    private ImageView play_controller_img,imgScreen,img_volume;
    private TextView current_text_tv,time_all;
    private SeekBar play_seek,volume_seek;
    private RelativeLayout view_layout;
    private ImageView  operation_bg, operation_percent;
    private FrameLayout fragement_layout; // 调节亮度和声音的控制台


    private boolean isFirst = true;// 是否第一次设置过总时间和总音量
    private static final int UPDATA_UI=1; // 更新时间进度

     private  int screen_width,screen_height;
//音频管理器
    private AudioManager mAudioManager;
  private boolean isFullScreen = false;  //标记是否全屏
    private  boolean isAdjust  =false ; //标记滑动是否合法
    private float lastX = 0, lastY = 0, threshold = 54;// 上一次滑动的结束位置和滑动最小范围
    private  float mBrightness ; //当前亮度值
    private VolumeReceiver mVolumeReceiver;// 声音的接收者
    private int mCurPlayPosition;// 当前播放进度

    /**
     * 刷新ui
     */
    private Handler  UIHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATA_UI:
                    //获取视频当前播放时间
                    int currentPosition = myVideoView.getCurrentPosition();
                    //获取视频的总时间
                    int  totalTime  = myVideoView.getDuration();
                    //格式化视频播放时间
                    updateTextViewWithTimeFormate(current_text_tv,currentPosition);
                    if(isFirst){
                        isFirst = false;
                        updateTextViewWithTimeFormate(time_all,totalTime);
                    }

                    play_seek.setMax(totalTime);
                    play_seek.setProgress(currentPosition);
                    //自己延迟调用
                    UIHandler.sendEmptyMessageDelayed(UPDATA_UI,500);

                    break;
                default:
                    break;
            }
         }
    };
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidieoplay);
         mAudioManager  = (AudioManager) getSystemService(AUDIO_SERVICE);//获取音频服务
         initView();
         setPlayerEvent();
         //申请权限
         permissionRequests(Manifest.permission.READ_EXTERNAL_STORAGE, this,this);
     }
    @Override
    protected void onResume() {
        super.onResume();
        if (myVideoView.canSeekForward() && mCurPlayPosition != 0) {
            myVideoView.seekTo(mCurPlayPosition);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (myVideoView.canPause()) {
            play_controller_img.setImageResource(R.drawable.pause_btn_style);
            mCurPlayPosition = myVideoView.getCurrentPosition();
            myVideoView.pause();
        }
        if (UIHandler.hasMessages(UPDATA_UI)) {
            UIHandler.removeMessages(UPDATA_UI);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (myVideoView.canPause()) {
            play_controller_img.setImageResource(R.drawable.play_btn_style);
            myVideoView.pause();
        }
        if (UIHandler.hasMessages(UPDATA_UI)) {
            UIHandler.removeMessages(UPDATA_UI);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myVideoView.canPause()) {
            myVideoView.stopPlayback();
        }
        if (UIHandler != null) {
            UIHandler.removeCallbacksAndMessages(0);// 移除所有消息
            UIHandler = null;
        }
        if (mVolumeReceiver != null) {
            unregisterReceiver(mVolumeReceiver);// 解注册
        }
    }
    /**
     *播放器的事件
     */
    private void setPlayerEvent() {
        /**
         * 控制视频都得播放或者暂停
         */
       play_controller_img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(myVideoView.isPlaying()){
                   play_controller_img.setBackgroundResource(R.drawable.play_btn_style);
                   //暂停播放
                   myVideoView.pause();
                   //停止更新ui
                   UIHandler.removeMessages(UPDATA_UI);
               }else {
                   play_controller_img.setBackgroundResource(R.drawable.pause_btn_style);
                   myVideoView.start();
                   UIHandler.sendEmptyMessageDelayed(UPDATA_UI,500);
               }
           }
       });
        /**
         * 播放进度条的滑动监听
         */
        play_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                   updateTextViewWithTimeFormate(current_text_tv,i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              UIHandler.removeMessages(UPDATA_UI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                  int progress = seekBar.getProgress();
                //令视频播放进度遵循seekbar的停止拖动的进度
                myVideoView.seekTo(progress);
                if(!myVideoView.isPlaying()){
                    myVideoView.start();
                    play_controller_img.setBackgroundResource(R.drawable.pause_btn_style);
                }

                UIHandler.sendEmptyMessage(UPDATA_UI);
            }
        });
        /**
         * 音量进度条的滑动监听
         */
        volume_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                /**
                 * 设置当前设备的音量
                 */
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /**
         *横竖屏的切换
         */
        imgScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFullScreen){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    imgScreen.setBackgroundResource(R.drawable.full_screen);

                }else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    imgScreen.setBackgroundResource(R.drawable.exit_full_screen);
                }
            }
        });
        /**
         * 控制VideoView的收拾控件
         */
        myVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //获取当前的x轴和y轴的滑动距离
                float x = motionEvent.getX();
                float y = motionEvent.getY();

                 switch (motionEvent.getAction()){

                     case MotionEvent.ACTION_DOWN :
                         lastX =x ;
                         lastY = y ;
                         break;
                     case MotionEvent.ACTION_MOVE :

                         float detlax = x -lastX;
                         float detlay = y -lastY;
                         //手指滑动是x轴和y轴的偏移量
                         float  absdetlax = Math.abs(detlax);
                         float  absdetlay = Math.abs(detlay);
                         /**
                          * 手势的合法性
                          */
                        if(absdetlax>threshold && absdetlay >threshold){
                                 if(absdetlax<absdetlay){
                                     isAdjust = true;
                                 }else {
                                     isAdjust = false;
                                 }
                        }else if(absdetlax<threshold  && absdetlay>threshold){
                           isAdjust = true;
                        }else if(absdetlax >threshold && absdetlay<threshold){
                            isAdjust = false;
                        }
                         Log.e("xyc", "onTouch: isAdjust =="+isAdjust);
                         if(isAdjust){
                             /**
                              * 在判断好当前手势事件已经合法的前提下，去区分此时手势应该调节亮度还是声音
                              *
                              * //把屏幕分成两部分 左部分控制亮度，右部分控制声音
                              */
                             if(x<screen_width/2){
                                   if(detlay>0){
                                       /**
                                        * 降低亮度
                                        */
                                   }else {
                                       /**
                                        * 升高亮度
                                        */
                                   }
                                 changeBrightness(-detlay);
                             }else {
                                 if(detlay>0){
                                     /**
                                      * 减小声音
                                      */
                                 }else {
                                     /**
                                      *增大声音
                                      */
                                 }
                                 changeVolume(-detlay);
                             }
                         }

                         lastX =x ;
                         lastY = y ;
                         break;
                     case MotionEvent.ACTION_UP:
                         lastX = 0;
                         lastY = 0;
                             fragement_layout.setVisibility(View.GONE);
                         break;
                 }

                return true;
            }
        });
    }

    /**
     * 滑动屏幕修改音量的大小
     * @param detlay
     */
    private  void  changeVolume(float detlay){

        Log.e("xyc", "changeVolume: "+detlay );
        //系统最大的音量值
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量值
        int  current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
           int index = (int) (detlay/screen_height*maxVolume*3);
            int volume = Math.max(current+index,0);
              mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0);
        volume_seek.setProgress(volume);
        if(fragement_layout.getVisibility() == View.GONE){
            fragement_layout.setVisibility(View.VISIBLE);
        }
        operation_bg.setImageResource(R.drawable.video_voice_bg);
        ViewGroup.LayoutParams layoutparams = operation_percent.getLayoutParams();
        layoutparams.width = (int) (DensityUtils.dp2px(94)* (float)volume/maxVolume);
        operation_percent.setLayoutParams(layoutparams);
    }

    /**
     * 滑动屏幕修改亮度的大小
     * @param detlay
     */
    private  void  changeBrightness(float detlay){
        Log.e("xyc", "changeBrightness: "+detlay );

        WindowManager.LayoutParams attributex = getWindow().getAttributes();
       mBrightness = attributex.screenBrightness;
        float index =  detlay/screen_height/3;
        mBrightness += index;
        //判断临界值
        if(mBrightness >1.0f){
            mBrightness = 1.0f ;
        }else if (mBrightness <0.1f){
            mBrightness = 0.1f;
        }
        if(fragement_layout.getVisibility() == View.GONE){
            fragement_layout.setVisibility(View.VISIBLE);
        }
        operation_bg.setImageResource(R.drawable.video_brightness_bg);
        ViewGroup.LayoutParams layoutparams = operation_percent.getLayoutParams();
        layoutparams.width = (int) (DensityUtils.dp2px(94)* mBrightness);
        operation_percent.setLayoutParams(layoutparams);


        attributex.screenBrightness = mBrightness;
        getWindow().setAttributes(attributex);
    }




    private void initView() {
        DensityUtils.init(this);
        mVolumeReceiver = new VolumeReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(mVolumeReceiver, intentFilter);// 注册声音广播接受者

        myVideoView = (VideoView) findViewById(R.id.videoView);
        controllerlinlayout = (LinearLayout) findViewById(R.id.lin_Left);
        play_controller_img = (ImageView) findViewById(R.id.img_pause);
        imgScreen = (ImageView) findViewById(R.id.img_screen);
        img_volume=(ImageView) findViewById(R.id.img_volume);
        current_text_tv = (TextView) findViewById(R.id.tv_time_current);
        time_all = (TextView) findViewById(R.id.tv_time_all);
        volume_seek = (SeekBar) findViewById(R.id.sb_volume);
        play_seek = (SeekBar) findViewById(R.id.sb_play);
        view_layout = (RelativeLayout) findViewById(R.id.view_layout);
       operation_bg = (ImageView) findViewById(R.id.opration_bg);
        operation_percent = (ImageView) findViewById(R.id.operation_percent);
        fragement_layout= (FrameLayout) findViewById(R.id.fragement_layout);


       screen_width= getResources().getDisplayMetrics().widthPixels;
        screen_height= getResources().getDisplayMetrics().heightPixels;
        /**
         * 获取设备最大的音量
         */
       int streamMaxVolume =  mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        /**
         * 获取设备当前的音量
         */
        int streamVolume =  mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume_seek.setMax(streamMaxVolume);
        volume_seek.setProgress(streamVolume);

    }

    /**
     * 屏幕切换的时候，需要的设置布局的大小
     * @param width
     * @param height
     */
    private void setVideoViewScale(int width ,int height ){
        ViewGroup.LayoutParams layoutParamss = myVideoView.getLayoutParams();
        layoutParamss.width = width;
        layoutParamss.height =height;
        myVideoView.setLayoutParams(layoutParamss);

        ViewGroup.LayoutParams layoutParames = view_layout.getLayoutParams();
        layoutParames.width = width;
        layoutParames.height =height;
        view_layout.setLayoutParams(layoutParames);
    }

    //格式化时间
    private void updateTextViewWithTimeFormate(TextView textView,int millisecond){
               int second = millisecond/1000;
                int hh = second/3600;
               int mm = second%3600/60;
                int ss = second%60;
        String str = null;
        if(hh!=0){
            str = String.format(Locale.CHINA,"%02d:%02d:%02d",hh,mm,ss);
        }else{
            str = String.format(Locale.CHINA,"%02d:%02d",mm,ss);
        }
        textView.setText(str);
    }

    @Override
    public void onClick(boolean bln) {
        if(bln){
            ToastUtils.showLToast(MyViedeoPlayerActivity.this,"权限通过");
            /**
             * 本地视频的播放
             */
            String path = Environment.getExternalStorageDirectory().getPath()+"/12345.mp4";
            myVideoView.setVideoPath(path);
            /**
             * 网络视频的播放
             */
//                myvideoPlay.setVideoURI(Uri.parse(""));
            //控制视频播放
//            MediaController controller = new MediaController(MyViedeoPlayerActivity.this);
            //控制与view关联
//            myVideoView.setMediaController(controller);
//            controller.setMediaPlayer(myVideoView);
            myVideoView.start();
            UIHandler.sendEmptyMessageDelayed(UPDATA_UI,500);

        }else{
            ToastUtils.showLToast(MyViedeoPlayerActivity.this,"权限拒绝");
        }
    }


    /**
     * 监听屏幕放心的改变
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        /**
         * 当屏幕方向是横屏的时候
         */
         if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                  setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                 img_volume.setVisibility(View.VISIBLE);
             volume_seek.setVisibility(View.VISIBLE);
             isFullScreen = true;
             // 强制移除半屏状态
             getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
             getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

         }
        /**
         * 当屏幕方向是竖屏的时候
         */
        else{
             setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(240));
             img_volume.setVisibility(View.GONE);
             volume_seek.setVisibility(View.GONE);
             isFullScreen = false;

             // 强制移除全屏状态
             getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
             getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
         }
    }
    // 音量的广播接收者，接收系统音量发生变化
    private class VolumeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (volume == 0) {
                    img_volume.setImageResource(R.drawable.mute);
                } else {
                    img_volume.setImageResource(R.drawable.volume);
                }
                volume_seek.setProgress(volume);
            }
        }
    }


    // 返回事件
    @Override
    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }
}
