package cn.efarm360.com.dabaomvp.weigt;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import cn.efarm360.com.dabaomvp.R;


/**
 *实现切换的button按钮
 */
public class SegmentGroupHelper {
    public static RadioButton createRadioButton(Context context, int id, String name) {
        RadioButton radioButton = (RadioButton) View.inflate(context, R.layout.radio_button_item, null);
        return initRadioButton(radioButton, id, name);
    }

    public static RadioButton createRadioButtonNoBorder(Context context, int id, String name) {
        RadioButton radioButton = (RadioButton) View.inflate(context, R.layout.tab_radio_item, null);
        return initRadioButton(radioButton, id, name);
    }

    private static RadioButton initRadioButton(RadioButton radioButton, int id, String name) {
        radioButton.setId(id);
        radioButton.setText(name);
        return radioButton;
    }
}
