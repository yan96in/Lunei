package me.drakeet.lunei.ui.login;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.lunei.R;
import me.drakeet.lunei.data.User;
import me.drakeet.lunei.util.ToastUtils;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends LoginActivity {

    @Bind(R.id.re_password) TextView mRePasswordView;
    @Bind(R.id.login_in_button) Button mLoginButton;


    @Override public boolean canBack() {
        return true;
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mRePasswordView.setVisibility(View.VISIBLE);
        mLoginButton.setText(R.string.sign_up);
    }


    protected void next(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.signUpInBackground(e -> {
            if (e == null) {
                ToastUtils.showShort(getString(R.string.sign_up_successful));
                onBackPressed();
            } else {
                showProgress(false);
                ToastUtils.showLongX2(e.getMessage());
            }
        });
    }


    @Override protected boolean isPasswordValid(String password) {
        // @formatter:off
        return password.length() > 4 &&
            mRePasswordView.getText()
                           .toString()
                           .equals(mPasswordView.getText().toString());
        // @formatter:on
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

