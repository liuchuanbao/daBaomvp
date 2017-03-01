package cn.efarm360.com.dabaomvp.moudle;

import java.util.ArrayList;

import cn.efarm360.com.dabaomvp.bean.WifiBean;

/**
 * Created by liuchuanbao on 2017/3/1.
 */

public interface IWifiModel {

     ArrayList<WifiBean> getWifis();
    void addWifi(WifiBean wifiBean);
    void delWifi(WifiBean wifiBean);
}
