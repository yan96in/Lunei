package me.drakeet.lunei;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/23 23:28
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, AppConfig.APP_KEY, AppConfig.APP_SECRET);
    }
}
