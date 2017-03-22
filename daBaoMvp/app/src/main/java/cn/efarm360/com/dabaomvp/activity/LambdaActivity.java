package cn.efarm360.com.dabaomvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.efarm360.com.dabaomvp.R;

/**
 *  lanbda 语法的使用 demo
 */
public class LambdaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        new Thread(() ->System.out.println("Run!")).start();
        Runnable r1 = () -> {System.out.println("Hello Lambda!");};
        Object o = (Runnable) () -> { System.out.println("hi"); }; // correct
    }
}
