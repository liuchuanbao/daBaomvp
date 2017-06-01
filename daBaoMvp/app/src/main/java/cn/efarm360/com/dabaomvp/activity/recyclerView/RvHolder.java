package cn.efarm360.com.dabaomvp.activity.recyclerView;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

import cn.efarm360.com.dabaomvp.R;

/**
 * Created by srtianxia on 2015/12/20.
 */
class RvHolder extends RecyclerView.ViewHolder  {
    private TextView textView;

    public RvHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setData(String item, int position){
        Set<Integer> positionSet = RvSelectActivity.instance.positionSet;
        if (positionSet.contains(position)) {
            itemView.setBackground(RvSelectActivity.instance.getResources().getDrawable(R.drawable.bg_selected));
        } else {
            itemView.setBackground(RvSelectActivity.instance.getResources().getDrawable(R.drawable.btn_common));
        }
        textView.setText(item);
    }

}
