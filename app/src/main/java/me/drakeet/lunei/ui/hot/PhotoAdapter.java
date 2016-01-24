package me.drakeet.lunei.ui.hot;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;
import me.drakeet.lunei.R;
import me.drakeet.lunei.data.Photo;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/23 23:58
 */
public class PhotoAdapter
        extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    List<Photo> mList;


    public PhotoAdapter(List<Photo> list) {
        mList = list;
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Photo data = mList.get(position);
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(v);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        @Bind(R.id.likeCount) TextView likeCount;


        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
            Typeface type = Typeface.createFromAsset(context.getAssets(),
                    "fonts/ProximaRegular.otf");
            likeCount.setTypeface(type);
        }
    }
}

