package cn.efarm360.com.dabaomvp.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.ZiDingYiview.SlidingButtonView;
import cn.efarm360.com.dabaomvp.utils.ScreenUtil;

/**
 * Created by pubinfo on 2017/5/4.
 */

public class SlidRecyclerViewAdapter  extends   RecyclerView.Adapter implements SlidingButtonView.IonSlidingButtonListener  {
    Context context;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<String> mDatas = new ArrayList<String>();

    private SlidingButtonView mMenu = null;

    public SlidRecyclerViewAdapter(Context context, ArrayList<String> date) {
        this.context = context;
        this.mDatas = date;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public SlidingButtonView slidingButtonView;
        public RelativeLayout  rl_left;
        public LinearLayout layout_content;
        public TextView btn_Delete;
        public RadioButton rbtn;
        public MyViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.text);
            slidingButtonView = (SlidingButtonView) v.findViewById(R.id.SlidingButtonView);
            rl_left = (RelativeLayout) v.findViewById(R.id.rl_left);
            layout_content = (LinearLayout) v.findViewById(R.id.layout_content);
            btn_Delete = (TextView) v.findViewById(R.id.tv_delete);
            rbtn = (RadioButton) v.findViewById(R.id.rbtn);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(view);
    }

    boolean allopen = false;

    public void setAllopen(boolean allopen) {
        this.allopen = allopen;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.slidingButtonView.setSlidingButtonListener(SlidRecyclerViewAdapter.this);

        viewHolder.textView.setText(mDatas.get(position));

        //设置内容布局的宽为屏幕宽度
        viewHolder.layout_content.getLayoutParams().width = ScreenUtil.getDisplayWidth(context) + viewHolder.rl_left.getLayoutParams().width;
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
//                if (menuIsOpen()) {
//                    closeMenu();//关闭菜单
//                } else {
//                    int n = viewHolder.getLayoutPosition();
//                    mIDeleteBtnClickListener.onItemClick(v, n);
//                }

            }
        });
        viewHolder.rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onItemClick(view,n);
            }
        });


        viewHolder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
//        LogUtils.d("项：" + position + "是否开:" + allopen);
        if (allopen) {
//            LogUtils.d("打开？");
            viewHolder.slidingButtonView.openMenu();
            viewHolder.slidingButtonView.setCanTouch(false);
        } else {
            viewHolder.slidingButtonView.closeMenu();
            viewHolder.slidingButtonView.setCanTouch(true);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     *
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }

    public void addData(int position) {
        mDatas.add(position, "添加项");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}
