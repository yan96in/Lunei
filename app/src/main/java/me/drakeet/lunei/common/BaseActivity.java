package me.drakeet.lunei.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.badoo.mobile.util.WeakHandler;
import me.drakeet.lunei.R;
import me.drakeet.lunei.util.ToastUtils;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 1/24/16 15:45
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog mDialog;
    private boolean mDoubleClickExit;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new ProgressDialog(this);
        mDialog.setIndeterminate(true);
        mDialog.setMessage("请稍候...");
        mDialog.setCanceledOnTouchOutside(false);
        if (canBack() && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    public void showProgress(boolean show) {
        if (show) {
            mDialog.show();
        } else {
            mDialog.dismiss();
        }
    }


    public void closeKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v != null) imm.showSoftInput(v, 0);
    }


    public boolean canBack() {
        return false;
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void doesDoubleClickExit() {
        if (mDoubleClickExit) {
            onBackPressed();
        } else {
            mDoubleClickExit = true;
            ToastUtils.showShort(R.string.double_click_to_exit);
            new WeakHandler().postDelayed(() -> mDoubleClickExit = false, 1200);
        }
    }

}
