package me.drakeet.lunei.ui.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import java.util.List;
import me.drakeet.lunei.R;
import me.drakeet.lunei.data.User;
import me.drakeet.lunei.ui.MainActivity;
import me.drakeet.lunei.ui.ProgressBarBaseActivity;
import me.drakeet.lunei.util.ToastUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends ProgressBarBaseActivity {

    // UI references.
    @Bind(R.id.user_name) AutoCompleteTextView mUserNameView;
    @Bind(R.id.password) EditText mPasswordView;
    @Bind(R.id.toolbar) Toolbar mToolbar;


    @Override public View provideToolbar() {
        return mToolbar;
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Set up the login form.
        populateAutoComplete();

        mPasswordView.setOnEditorActionListener((v, id, event) -> {
            if (id == EditorInfo.IME_ACTION_DONE) {
                attemptLogin();
                return true;
            }
            return false;
        });
        setSupportActionBar(mToolbar);
    }


    private void populateAutoComplete() {
        //...
    }


    @OnClick(R.id.sign_in_button) void attemptLogin() {
        // TODO: 16/1/24 reset request

        mUserNameView.setError(null);
        mPasswordView.setError(null);
        boolean cancel = false;
        View focusView = null;
        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;
        } else if (!isUserNameValid(userName)) {
            mUserNameView.setError(getString(R.string.error_invalid_user_name));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            login(userName, password);
        }
    }


    private void login(String userName, String password) {
        User.logInInBackground(userName, password, new LogInCallback<AVUser>() {
            @Override public void done(AVUser user, AVException e) {
                if (e == null) {
                    startActivity(MainActivity.class);
                } else {
                    showProgress(false);
                    ToastUtils.showShort(R.string.error_incorrect_password);
                }
            }
        });
    }


    private boolean isUserNameValid(String name) {
        return name != null && name.length() > 1 && name.length() < 20;
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    @Deprecated private void addUserNamesToAutoComplete(List<String> list) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this,
                android.R.layout.simple_dropdown_item_1line, list);
        mUserNameView.setAdapter(adapter);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
}

