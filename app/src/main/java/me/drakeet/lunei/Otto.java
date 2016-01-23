package me.drakeet.lunei;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 15/11/3 11:14
 */
public class Otto {

    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);


    public static Bus getSeat() {return BUS;}


    private Otto() {}
}