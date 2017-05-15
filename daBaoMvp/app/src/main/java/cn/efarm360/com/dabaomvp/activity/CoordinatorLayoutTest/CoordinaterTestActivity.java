package cn.efarm360.com.dabaomvp.activity.CoordinatorLayoutTest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import cn.efarm360.com.dabaomvp.R;

public class CoordinaterTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String GITHUB_REPO_URL = "https://github.com/liuchuanbao/liuchuanbao.github.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinater_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.coordinater_test_toobar);
        toolbar.inflateMenu(R.menu.menu_coordinator_test);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_REPO_URL));
                startActivity(browserIntent);
                return true;
            }
        });

        findViewById(R.id.main_coordinator_textview).setOnClickListener(this);
        findViewById(R.id.main_materialup_textview).setOnClickListener(this);
        findViewById(R.id.main_ioexample_textview).setOnClickListener(this);
        findViewById(R.id.main_space_textview).setOnClickListener(this);
        findViewById(R.id.main_swipebehavior_textview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_coordinator_textview:
                SimpleCoordinatorActivity.start(this);
                break;

            case R.id.main_ioexample_textview:
                IOActivityExample.start(this);
                break;

            case R.id.main_space_textview:
                FlexibleSpaceExampleActivity.start(this);
                break;

            case R.id.main_materialup_textview:
                MaterialUpConceptActivity.start(this);
                break;

            case R.id.main_swipebehavior_textview:
                SwipeBehaviorExampleActivity.start(this);
                break;
        }
    }
}
