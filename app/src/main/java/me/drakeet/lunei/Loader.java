package me.drakeet.lunei;

import android.content.Context;
import android.support.annotation.NonNull;
import bz.tsung.android.objectify.NoSuchPreferenceFoundException;
import bz.tsung.android.objectify.ObjectPreferenceLoader;
import me.drakeet.lunei.data.User;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 17:37
 */
public class Loader {

    private Context context;


    public Loader(Context context) {
        this.context = context;
    }


    public void putCurrentUserName(@NonNull String name) {
        new ObjectPreferenceLoader(context, "current_user_name",
                String.class).save(name);
    }


    public String getCurrentUserName() {
        try {
            return new ObjectPreferenceLoader(context, "current_user_name",
                    String.class).load();
        } catch (NoSuchPreferenceFoundException e) {
            return null;
        }
    }
}
