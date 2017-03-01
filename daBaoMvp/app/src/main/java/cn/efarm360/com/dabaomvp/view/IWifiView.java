package cn.efarm360.com.dabaomvp.view;

import android.view.View;

import java.util.ArrayList;

import cn.efarm360.com.dabaomvp.bean.WifiBean;

/**
 * Created by liuchuanbao on 2017/3/1.
 */

public interface IWifiView {

    void setListViewData(ArrayList<WifiBean> list);

    void showSnackbar(View view);
}
