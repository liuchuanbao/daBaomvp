package cn.efarm360.com.dabaomvp.moudle;

import java.util.ArrayList;

import cn.efarm360.com.dabaomvp.bean.WifiBean;

/**
 * Created by liuchuanbao on 2017/3/1.
 */

public class WifiModelImpl implements IWifiModel {
    //模拟获取数据
    @Override
    public ArrayList<WifiBean> getWifis() {
        ArrayList<WifiBean> list = new ArrayList<WifiBean>();
        for(int i = 0 ;i<15;i++) {
           WifiBean bean =   new WifiBean();
            bean.setmComment("大宝"+i+"号");
            bean.setmSsid("12345"+i);
            list.add(bean);
        }
        return list;
    }

    @Override
    public void addWifi(WifiBean wifiBean) {

    }

    @Override
    public void delWifi(WifiBean wifiBean) {

    }
}
