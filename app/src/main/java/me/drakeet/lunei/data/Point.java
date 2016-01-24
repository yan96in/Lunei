package me.drakeet.lunei.data;

import android.os.Parcel;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 14:53
 */
@AVClassName("Point") public class Point extends AVObject {

    public Point() {
    }


    public Point(Parcel in) {
        super(in);
    }


    public static final Creator CREATOR = AVObjectCreator.instance;


    public void increment() {
        increment("points");
    }


    public void increment(int p) {
        increment("points", p);
    }
}