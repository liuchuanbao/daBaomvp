package cn.efarm360.com.dabaomvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.bean.WifiBean;

/**
 * Created by liuchuanbao on 2017/3/1.
 */

public class IWifiAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflate ;
    ArrayList<WifiBean> mdata =new ArrayList<WifiBean>();
    public List<WifiBean> getMdata() {
        return mdata;
    }

    public void setMdata(ArrayList<WifiBean> mdata) {
        this.mdata = mdata;
    }
  public  IWifiAdapter(Context con){
      this.context =con;
      inflate = LayoutInflater.from(con);
  }


    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return mdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder = null;
        if(view != null){
            holder = (Viewholder) view.getTag();
        }else {
            view = inflate.inflate(R.layout.layout_list_wifi,null);
            holder = new Viewholder();
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvNumber = (TextView) view.findViewById(R.id.tv_number);
            view.setTag(holder);
        }
        holder.tvName.setText(mdata.get(i).getmSsid());
        holder.tvNumber.setText(mdata.get(i).getmComment());

        return view;
    }
    static  class Viewholder{
        TextView tvName,tvNumber;
    }

}
