package cn.efarm360.com.dabaomvp.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.weigt.SegmentGroupHelper;
import cn.efarm360.com.dabaomvp.weigt.SegmentedGroup;

public class SegmentGroupActivity extends AppCompatActivity {

    private SegmentedGroup mSegmentGroup;
    private ArrayList<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_ziding);
        initView();
        initData();
    }

    private void initView() {
        mSegmentGroup = (SegmentedGroup) findViewById(R.id.segment_group_sg);
    }

    private void initData() {
        // 添加模拟数据
        mNames.add("路飞");
        mNames.add("佐罗");
        mNames.add("乌索普");
        mNames.add("弗兰奇");
        // 添加子view
        for (int x = 0; x < mNames.size(); x++) {
            RadioButton radioButton = SegmentGroupHelper.createRadioButton(this, x, mNames.get(x));
            mSegmentGroup.addView(radioButton);
            //  mSegmentGroup.updateBackground();
        }

        mSegmentGroup.setCheckColor(Color.BLACK); // 设置被选中背景色
        mSegmentGroup.setStrokeColor(Color.BLACK); // 设置边线颜色
        mSegmentGroup.updateBackground(); // 更新背景颜色的核心方法，不要忘记调用

        // 设置点击监听
        mSegmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(SegmentGroupActivity.this, "name = [" + mNames.get(checkedId) + "], checkedId = [" + checkedId + "]", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
