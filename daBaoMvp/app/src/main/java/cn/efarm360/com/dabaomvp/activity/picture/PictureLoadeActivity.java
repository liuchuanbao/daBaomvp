package cn.efarm360.com.dabaomvp.activity.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import cn.efarm360.com.dabaomvp.R;

public class PictureLoadeActivity extends AppCompatActivity {
    private int change=0;
    private int[] ids = new int[] { R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5 };
    private Drawable[] drawables;
    private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_loade);
        imageView = (ImageView) findViewById(R.id.imgView);

        /*获得合适的drawable资源*/
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), R.drawable.img1, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 500 * 500);
        opts.inJustDecodeBounds = false;

        drawables=new Drawable[ids.length];

        try {
            for (int i = 0; i < ids.length; i++) {// for循环，加载5个drawable资源
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), ids[i], opts);
                drawables[i] = new BitmapDrawable(bmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //开启线程，改变transition
        new Thread(new MyRunnable()).start();

    }

    //处理transition的改变
    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            int duration=msg.arg1;
            TransitionDrawable transitionDrawable=null;
            transitionDrawable= new TransitionDrawable(new Drawable[] {
                    drawables[change%ids.length],//实现从0 1 2 3 4 5 0 1 2.。。这样的不停转变
                    drawables[(change+1)%ids.length] });
            change++;
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(duration);
            return false;
        }
    });

    //线程，去发送消息，让transition一直改变
    private class MyRunnable implements Runnable{
        public void run() {
            while (true) {
                int duration=3000;//改变的间隔
                Message message=handler.obtainMessage();
                message.arg1=duration;
                handler.sendMessage(message);
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //计算合适的图片大小
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength,
                                        int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    //计算合适的图片大小
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength,
                                                int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h
                / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
