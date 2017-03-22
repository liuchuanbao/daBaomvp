package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取因为一些配置信息，多处用到的。且以后可能变更的，我想写个.prorperties配置文件给管理起来。
 我把配置文件放在了assets文件夹下
 *
 * app.properties  文件名
 *
 * 使用
 Properties proper = ProperTies.getProperties(getApplicationContext());
 String appName = proper.getProperty("appname");
 Log.i("TAG", "appname=" + appName);
 */
public class ProperTies {
	 public static Properties getProperties(Context c){
	        Properties appProps;
	        Properties props = new Properties();
	        try {
	            //方法一：通过activity中的context攻取setting.properties的FileInputStream
	            //注意这地方的参数appConfig在eclipse中应该是appConfig.properties才对,但在studio中不用写后缀
	            //InputStream in = c.getAssets().open("appConfig.properties");
	            InputStream in = c.getAssets().open("app.properties");
	            //方法二：通过class获取setting.properties的FileInputStream
	            //InputStream in = PropertiesUtill.class.getResourceAsStream("/assets/  setting.properties "));
	            props.load(in);
	        } catch (Exception e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	        appProps = props;
	        return appProps;
	    }

}
