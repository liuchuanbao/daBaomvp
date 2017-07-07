package cn.efarm360.com.dabaomvp.activity.webservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import cn.efarm360.com.dabaomvp.R;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class WebServiceActivity extends AppCompatActivity {

    Button btnClick,btnClick2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        btnClick = (Button) findViewById(R.id.btn_click);
        btnClick2= (Button) findViewById(R.id.btn_click2);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    requestData();

                    }
                }).start();
                
            }
        });

        btnClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Net.newInstance().getSupportCity("山东")
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("失败", "onError: " + e.getMessage());
                            }

                            @Override
                            public void onNext(String s) {
                                Log.e("成功", "onNext: " + s);
                            }
                        });
            }
        });

    }

    private void requestData() {
        String namespace = "http://WebXml.com.cn/";
        String transUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
        String method = "getSupportCity";
     //注意版本使用，这个需要跟后台询问或者从wsdl文档或者服务说明中查看
        int envolopeVersion = SoapEnvelope.VER12;
         //可能是namspace+method拼接  ver11需要用到
        String soapAction = "http://WebXml.com.cn/getSupportCity";
        SoapObject request = new SoapObject(namespace, method);
     //参数一定注意要有序，尽管是addProperty（），不要当作HttpUrl可以使用LinkedHashMap封装
        request.addProperty("byProvinceName", "湖北");
//                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVersion);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE se = new HttpTransportSE(transUrl);
        try {
            //                    se.call(soapAction, envelope);    //ver11，第一个参数不能为空
            se.call(null, envelope);//envolopeVersion为ver12第一个参数可以为空，必须接口支持ver12才行
            SoapObject response = (SoapObject) envelope.bodyIn;
            //response的处理需要根据返回的具体情况，基本都要进行下面一步
            SoapObject o = (SoapObject) response.getProperty(0);
            //当前方法返回的结果为一个数组
            Log.e("xyc", "MainActivity.java->run(): size=" + o.getPropertyCount());
            for (int i = 0; i < o.getPropertyCount(); i++) {
                Log.e("xyc", "MainActivity.java->run(): ==" + o.getPropertyAsString(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }


}
