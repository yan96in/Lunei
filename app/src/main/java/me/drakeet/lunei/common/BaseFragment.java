package me.drakeet.lunei.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 1/23/16 20:37
 */
public class BaseFragment extends Fragment {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override public void onDestroy() {
        super.onDestroy();
    }


    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }


    public void setTitle(int sid) {
        getActivity().setTitle(sid);
    }
}
