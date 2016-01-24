package me.drakeet.lunei.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import me.drakeet.lunei.Loader;
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
    Loader mLoader;


    @Override protected int provideContentViewId() {
        return R.layout.activity_login;
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mLoader = new Loader(this);
        populateAutoComplete();

        mPasswordView.setOnEditorActionListener((v, id, event) -> {
            if (id == EditorInfo.IME_ACTION_DONE) {
                attemptLogin();
                return true;
            }
            return false;
        });
    }


    private void populateAutoComplete() {
        String userName = mLoader.getCurrentUserName();
        if (userName != null) {
            mUserNameView.setText(userName);
        }
        //todo ...
    }


    @OnClick(R.id.login_in_button) void attemptLogin() {
        mUserNameView.setError(null);
        mPasswordView.setError(null);
        boolean cancel = false;
        View focusView = null;
        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();
        mLoader.putCurrentUserName(userName);

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
            next(userName, password);
        }
    }


    protected void next(String userName, String password) {
        User.logInInBackground(userName, password, (user, e) -> {
            if (e == null) {
                startActivity(MainActivity.class);
            } else {
                showProgress(false);
                ToastUtils.showShort(R.string.error_incorrect_password);
            }
        });
    }


    private boolean isUserNameValid(String name) {
        return name != null && name.length() > 1 && name.length() < 20;
    }


    protected boolean isPasswordValid(String password) {
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


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sign_up) {
            startActivity(SignUpActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
}

