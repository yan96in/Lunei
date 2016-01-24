package me.drakeet.lunei;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import me.drakeet.lunei.data.Point;
import me.drakeet.lunei.data.User;

import static me.drakeet.lunei.Keys.APP_KEY;
import static me.drakeet.lunei.Keys.APP_SECRET;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/23 23:28
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        AVObject.registerSubclass(User.class);
        AVObject.registerSubclass(Point.class);
        AVOSCloud.initialize(this, APP_KEY, APP_SECRET);
    }
}
