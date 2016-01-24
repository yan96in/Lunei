package me.drakeet.lunei.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 14:23
 */
public class FontTextView extends TextView {
    private static final String TAG = "FontTextView";


    public FontTextView(Context context) {
        super(context);
        setCustomFont(context);
    }


    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }


    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }


    private void setCustomFont(Context context) {
        setCustomFont(context, "Georgia.ttf");
    }


    public boolean setCustomFont(Context context, String asset) {
        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    "fonts/" + asset);
        } catch (Exception e) {
            Log.e(TAG, "Unable to load typeface in /assets/fonts/: " +
                    e.getMessage());
            return false;
        }
        setTypeface(typeface);
        return true;
    }
}
