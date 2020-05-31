package khi.fast.log.pre_login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import khi.fast.log.R;
import khi.fast.log.utils.Utils;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseAuth mFirebaseAuth;
    public static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        mFirebaseAuth = FirebaseAuth.getInstance();
        AuthListener();

    }
    private void AuthListener() {

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Utils.startingActivity(LoginActivity.this, QuestionActivity.class,"","",true);

                } else {


                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.AppTheme)
                                    .setLogo(R.drawable.flog_logo)
                                    .setProviders(
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }
            }
        };
    }

    @Override
    public void onResume() {

        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
    @Override
    public void onPause() {

        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }

    }


}
