package me.drakeet.lunei.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.lunei.R;
import me.drakeet.lunei.common.BaseActivity;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @Bind(R.id.pager) ViewPager mViewPager;

    MainPagerAdapter mPagerAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initViewPager();
        initTabLayout();
    }


    private void initViewPager() {
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);
    }


    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                doesDoubleClickExit();
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
