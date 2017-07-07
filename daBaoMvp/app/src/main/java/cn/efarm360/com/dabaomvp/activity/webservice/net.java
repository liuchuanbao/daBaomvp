package cn.efarm360.com.dabaomvp.activity.webservice;


import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pubinfo on 2017/7/5.
 */

public class Net {
    private static final String TAG = "Net";
    String SERVICE_NAMESPACE = "http://WebXml.com.cn/";
    //大家不要试公司接口我做了假处理
    String SERVICE_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
    String methodName = "getSupportCity";

    public static Net newInstance() {
        Net fragment = new Net();
        return fragment;
    }

    public Observable<String>  getSupportCity(String paras){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                SoapObject request = new SoapObject(SERVICE_NAMESPACE, methodName);
//                Iterator it = paras.keySet().iterator();
//                while (it.hasNext()) {
//                    String key = it.next().toString();
                //参数一定注意要有序，尽管是addProperty（），不要当作HttpUrl可以使用LinkedHashMap封装
                request.addProperty("byProvinceName", paras);
//                }

                final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                envelope.dotNet = true;
                //envelope.bodyOut = request;
                // 创建HttpTransportSE传输对象
                final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
                envelope.setOutputSoapObject(request);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用webservice
                        try {
//                            ht.call(SERVICE_NAMESPACE + "/" + methodName, envelope);
                            ht.call(null, envelope);
                            // 获取服务器响应返回的SOAP消息
                            SoapObject responseSoap = (SoapObject) envelope.bodyIn;
                            Log.d("xyc", "run: "+responseSoap.getProperty(0).toString());
                            String getS = responseSoap.getProperty(0).toString();
                            subscriber.onNext(getS);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return observable;
    }
}
