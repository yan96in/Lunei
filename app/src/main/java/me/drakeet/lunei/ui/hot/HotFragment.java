package me.drakeet.lunei.ui.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.lunei.R;
import me.drakeet.lunei.common.BaseFragment;
import me.drakeet.lunei.data.Photo;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/23 23:52
 */
public class HotFragment extends BaseFragment {

    @Bind(R.id.list) RecyclerView mRecyclerView;

    PhotoAdapter mAdapter;
    List<Photo> mPhotos;

    public HotFragment() {
    }


    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    private void parseArguments() {
        Bundle bundle = this.getArguments();
    }


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mPhotos.add(new Photo());
        }
        mAdapter = new PhotoAdapter(mPhotos);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hot, container,
                false);
        ButterKnife.bind(this, rootView);
        setupRecyclerView();
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),
                3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
