package me.drakeet.lunei.data;

import android.os.Parcel;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import me.drakeet.lunei.callable.OnLogInCallback;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 14:52
 */
@AVClassName("_User") public class User extends AVUser {

    public User() {
    }


    public User(Parcel in) {
        super(in);
    }


    public static final Creator CREATOR = AVObjectCreator.instance;


    public void setNickname(String nickname) {
        put("nickname", nickname);
    }


    public String getNickname() {
        return getString("nickname");
    }


    public void setAvatar(String avatar) {
        put("avatar", avatar);
    }


    public String getAvatar() {
        return getString("avatar");
    }


    public void setSex(String sex) {
        put("sex", sex);
    }


    public String getSex() {
        return getString("sex");
    }


    public static boolean isLogin() {
        return AVUser.getCurrentUser() != null;
    }


    public static User getCurrentUser() {
        return getCurrentUser(User.class);
    }


    public Point getPoint() {
        try {
            return getAVObject("point", Point.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getInstallationId() {
        return getString("installationId");
    }


    public static void logInInBackground(String username, String password, OnLogInCallback callback) {
        logInInBackground(username, password, new LogInCallback<User>() {
            @Override public void done(User user, AVException e) {
                callback.done(user, e);
            }
        }, User.class);
    }
}
