package cn.efarm360.com.dabaomvp.ZiDingYiview;

/**
 * Created by pubinfo on 2017/4/17.
 */

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/***
 * 字体颜色变色，点击颜色跳转
 *
 * @author zhongwr
 * @update 2015年12月23日 下午2:52:41
 */
public class TextViewClickableSpan extends ClickableSpan {

    /** 变颜色的字体内容 **/
    private String content;
    /** 监听view回调 */
    private OnTextViewClickListener onClickListener;
    /** 字体颜色 */
    private int textViewColor = -1;

    public TextViewClickableSpan(String content) {
        super();
        this.content = content;
    }

    public TextViewClickableSpan(String content, int textViewColor) {
        this(content);
        this.textViewColor = textViewColor;
    }

    public TextViewClickableSpan(String content, OnTextViewClickListener onClickListener) {
        this(content);
        this.onClickListener = onClickListener;
    }

    public TextViewClickableSpan(String content, int textViewColor, OnTextViewClickListener onClickListener) {
        this(content, textViewColor);
        this.onClickListener = onClickListener;
    }

    /***
     *
     * @description 追加可点击，变色的文本内容
     * @author zhongwr
     * @params textView 追加在这个textView后边
     * @update 2015年12月23日 下午3:22:00
     */
    public void append(TextView textView) {
        if (!TextUtils.isEmpty(content) && null != textView) {
            SpannableString spanttt = new SpannableString(content);
            spanttt.setSpan(this, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.append(content);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /**
     * 字体颜色
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        if (-1 != textViewColor) {
//            ds.setColor(textViewColor);
            // 需要手动的修改颜色
            ds.setColor(Color.RED);
        }
    }

    @Override
    public void onClick(View widget) {
        if (null != onClickListener) {
            onClickListener.onTextViewClick(widget);
        }
    }

    public interface OnTextViewClickListener {
        public void onTextViewClick(View view);
    }
}