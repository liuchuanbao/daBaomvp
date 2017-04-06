package cn.efarm360.com.dabaomvp.adapter.MultItemAdapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import cn.efarm360.com.dabaomvp.bean.MultitItem.ChatMessage;

/**
 * Created by pubinfo on 2017/4/6.
 */

public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}
