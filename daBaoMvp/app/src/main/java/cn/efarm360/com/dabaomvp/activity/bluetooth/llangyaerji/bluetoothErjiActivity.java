package cn.efarm360.com.dabaomvp.activity.bluetooth.llangyaerji;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.utils.ToastUtil;

public class bluetoothErjiActivity extends AppCompatActivity {

    /**
     * 最近，项目了有这么一个工作需求，当用户正在使用我们产品里提供的音乐播放器播放音乐时，用户把耳机或者蓝牙耳机接入后，
     * 过一会儿，用户又把耳机给拔除，或者断开蓝牙耳机的连接，我们需要暂停播放音乐，

     定义一个BroadcastReceiver对象，  对于有线耳机，监听Intent.ACTION_HEADSET_PLUG系统广播，对于蓝牙耳机，监听BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED系统广播
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_erji);
    }

//    private void registerHeadsetPlugReceiver() {
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
//        registerReceiver(headsetPlugReceiver, intentFilter);
//
//        // for bluetooth headset connection receiver
//        IntentFilter bluetoothFilter = new IntentFilter(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
//        registerReceiver(headsetPlugReceiver, bluetoothFilter);
//    }
//
//    private BroadcastReceiver headsetPlugReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            String action = intent.getAction();
//            if (BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
//                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//                if(BluetoothProfile.STATE_DISCONNECTED == adapter.getProfileConnectionState(BluetoothProfile.HEADSET)) {
//                    //Bluetooth headset is now disconnected
////                    handleHeadsetDisconnected();
//                }
//            } else if ("android.intent.action.HEADSET_PLUG".equals(action)) {
//                if (intent.hasExtra("state")) {
//                    if (intent.getIntExtra("state", 0) == 0) {
////                        handleHeadsetDisconnected();
//                    }
//                }
//            }
//        }
//
//    };

    /**
     * 从硬件层面来看，直接监听耳机拔出事件不难，耳机的拔出和插入，会引起手机电平的变化，然后触发什么什么中断，

     最终在stack overflow找到答案，监听Android的系统广播AudioManager.ACTION_AUDIO_BECOMING_NOISY，
     但是这个广播只是针对有线耳机，或者无线耳机的手机断开连接的事件，
     监听不到有线耳机和蓝牙耳机的接入，但对于我的需求来说足够了，监听这个广播就没有延迟了，UI可以立即响应
     */
    private void registerHeadsetPlugReceiver() {
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(headsetPlugReceiver, intentFilter);
    }

    private BroadcastReceiver headsetPlugReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(action)) {
//                handleHeadsetDisconnected();
                ToastUtil.showToast(bluetoothErjiActivity.this,"断开连接");
                //todo
            }
        }

    };
}
