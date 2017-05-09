package cn.efarm360.com.dabaomvp.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import cn.efarm360.com.dabaomvp.R;

public class OpenLightActivity extends AppCompatActivity implements  SurfaceHolder.Callback {

    Button btn_open, btn_closse;
    private Camera camera;

    /**
     * SurfaceView 的使用
     * 1，创建一个SurfaceView对象；
     * 2, 获取 SurfaceHolder surfaceHolder = surfaceView.getHolder();
     * 3，创建 实现一个implements  SurfaceHolder.Callback 的接口的类
     * 4， 将第3步创建的类传入SurfaceView对象（surfaceHolder.addCallback(3的类);）
     */
    SurfaceView surfaceView;
    SurfaceHolder sfh;


    private Timer mTimer;
    private MyTimerTask mTimerTask;
    int Y_axis[],//保存正弦波的Y轴上的点
            centerY,//中心线
            oldX,oldY,//上一个XY点
            currentX;//当前绘制到的X轴上的点



    View.OnClickListener mImageClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_light);

        btn_open = (Button) findViewById(R.id.btn_open);
        btn_closse = (Button) findViewById(R.id.btn_close);



        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainHandler(btn_open);
            }
        });

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        sfh  = surfaceView.getHolder();
        sfh.addCallback(this);
        sfh.setType(3);
        surfaceView.setOnClickListener(this.mImageClickListener);

        //动态绘制正弦波的定时器
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();

        // 初始化y轴数据
        centerY = (getWindowManager().getDefaultDisplay().getHeight() - surfaceView
                .getTop()) / 2;
        Y_axis = new int[getWindowManager().getDefaultDisplay().getWidth()];
        for (int i = 1; i < Y_axis.length; i++) {// 计算正弦波
            Y_axis[i - 1] = centerY
                    - (int) (100 * Math.sin(i * 2 * Math.PI / 180));
        }
        btn_closse.setOnClickListener(new ClickEvent());

        EventBus.getDefault().post("这是个测试的");

    }

    /**
     * 开启和关闭闪光灯
     * @param view
     */
    public void mainHandler(View view) {
        if (camera == null) {
            camera = Camera.open();
        }
        camera.startPreview();
        Camera.Parameters parameter = camera.getParameters();
        if (Camera.Parameters.FLASH_MODE_TORCH.equals(parameter.getFlashMode())) {
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
            camera.release();
            camera = null;
        } else if (Camera.Parameters.FLASH_MODE_OFF.equals(parameter.getFlashMode())) {
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v == btn_closse) {
                SimpleDraw(Y_axis.length-1);//直接绘制正弦波

            } else if (v == btn_open) {
                oldY = centerY;
                mTimer.schedule(mTimerTask, 0, 5);//动态绘制正弦波
            }

        }

    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {

            SimpleDraw(currentX);
            currentX++;//往前进
            if (currentX == Y_axis.length - 1) {//如果到了终点，则清屏重来
                ClearDraw();
                currentX = 0;
                oldY = centerY;
            }
        }

    }

    /*
     * 绘制指定区域
     */
    void SimpleDraw(int length) {
        if (length == 0)
            oldX = 0;
        Canvas canvas = sfh.lockCanvas(new Rect(oldX, 0, oldX + length,
                getWindowManager().getDefaultDisplay().getHeight()));// 关键:获取画布
        Log.i("Canvas:",
                String.valueOf(oldX) + "," + String.valueOf(oldX + length));

        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);// 画笔为绿色
        mPaint.setStrokeWidth(2);// 设置画笔粗细

        int y;
        for (int i = oldX + 1; i < length; i++) {// 绘画正弦波
            y = Y_axis[i - 1];
            canvas.drawLine(oldX, oldY, i, y, mPaint);
            oldX = i;
            oldY = y;
        }
        sfh.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
    }

    void ClearDraw() {
        Canvas canvas = sfh.lockCanvas(null);
        canvas.drawColor(Color.BLACK);// 清除画布
        sfh.unlockCanvasAndPost(canvas);

    }

}
