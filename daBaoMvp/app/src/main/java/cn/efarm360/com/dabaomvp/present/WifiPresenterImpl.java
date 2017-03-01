package cn.efarm360.com.dabaomvp.present;

import android.util.Log;

import cn.efarm360.com.dabaomvp.moudle.IWifiModel;
import cn.efarm360.com.dabaomvp.moudle.WifiModelImpl;
import cn.efarm360.com.dabaomvp.view.IWifiView;

/**
 * Created by liuchuanbao on 2017/3/1.
 */

public class WifiPresenterImpl implements IWifiPresenter {

    private IWifiModel model;
    private IWifiView view;

    public WifiPresenterImpl (IWifiView mview){
        this.view = mview;
        model = new WifiModelImpl();
    }
    @Override
    public void onCreate() {
        setWifiDatas();
    }
    //给adapter
    private void setWifiDatas() {
        view.setListViewData(model.getWifis());
        Log.e("xcy", "setListViewData: model.getWifis() = "+ model.getWifis().size());
    }

}
