package cn.efarm360.com.dabaomvp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.fragement.RecycleViewFragment;

public class RecyclerActivity extends AppCompatActivity {

    /**
     * 实现recycleView的滑动删除
     * @param savedInstanceState
     */

    RecycleViewFragment mStoriesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initFragment();
    }
    private void initFragment() {
        mStoriesFragment = new RecycleViewFragment();
        switchFragment(mStoriesFragment, "首页");
    }

    public void switchFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_layout, fragment)
                .commit();
    }
}
