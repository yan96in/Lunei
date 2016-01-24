package me.drakeet.lunei.ui;

import android.os.Bundle;
import com.umeng.analytics.MobclickAgent;
import me.drakeet.lunei.common.BaseActivity;
import me.drakeet.lunei.data.User;
import me.drakeet.lunei.ui.login.LoginActivity;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 15:40
 */
public class SplashActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        //setContentView(R.layout.activity_splash);
    }


    /**
     * Subscribe and waiting for a login event to keep cookies live and start
     * main page.
     */
    public void checkLogin() {
        if (User.isLogin()) {
            startActivity(MainActivity.class);
            this.finish();
        } else {
            startActivity(LoginActivity.class);
            this.finish();
        }
    }


    @Override public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }


    @Override public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
