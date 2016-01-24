package me.drakeet.lunei.callable;

import com.avos.avoscloud.AVException;
import me.drakeet.lunei.data.User;

/**
 * Created by drakeet on 16/1/24.
 */
public interface OnLogInCallback {
    void done(User user, AVException e);
}
