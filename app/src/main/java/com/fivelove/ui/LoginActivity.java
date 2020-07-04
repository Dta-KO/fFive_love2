package com.fivelove.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.fivelove.R;
import com.fivelove.databinding.ActivityLoginBinding;
import com.fivelove.utils.Constant;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

import static com.firebase.ui.auth.BuildConfig.BUILD_TYPE;
import static com.fivelove.utils.Constant.RC_SIGN_IN;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private CallbackManager manager;
    private LoginButton loginButton;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init login fb
        loginByFacebook();
        binding.btnLoginFb.setOnClickListener(view -> loginButton.performClick());

        //init login number phone
        binding.btnLoginByPhoneNumber.setOnClickListener(view -> loginByPhoneNumber());

    }

    private void loginByFacebook() {
        manager = CallbackManager.Factory.create();
        loginButton = binding.loginButton;
        loginButton.setPermissions("email", "public_profile");
        loginButton.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkProfileUser();

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Constant.AUTH.signInWithCredential(credential).addOnCompleteListener(this, (Task<AuthResult> task) ->
                checkProfileUser());
    }

    public void checkProfileUser() {
        if (Constant.CURRENT_USER != null) {
            Log.d(TAG,Constant.CURRENT_USER.getDisplayName()+": "+Constant.CURRENT_USER.getPhotoUrl());
            if (Constant.CURRENT_USER.getDisplayName()==null || Constant.CURRENT_USER.getPhotoUrl().toString().isEmpty()) {
                changeLoginActivityToProfileActivity();
            } else {
                changeLoginActivityToMainActivity();
            }
        }
    }

    private void changeLoginActivityToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void changeLoginActivityToProfileActivity() {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //result login by fb
        manager.onActivityResult(requestCode, resultCode, data);

        //result login by phone number
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    changeLoginActivityToMainActivity();
                } else {
                    if (response != null) {
                        Log.d("Error", String.valueOf(response.getError()));
                    }
                }
            }
        }
    }

    private void loginByPhoneNumber() {
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setIsSmartLockEnabled(Boolean.parseBoolean(BUILD_TYPE))
                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.PhoneBuilder().build()))
                .setLogo(R.mipmap.ic_launcher)
                .build();
        startActivityForResult(intent, RC_SIGN_IN);
    }

}