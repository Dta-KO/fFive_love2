package com.fivelove.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.BuildConfig;
import com.firebase.ui.auth.IdpResponse;
import com.fivelove.R;
import com.fivelove.databinding.ActivityLoginBinding;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static com.fivelove.utils.Constant.RC_SIGN_IN;

public class LoginActivity extends BaseActivity {
    private FirebaseAuth auth;
    ActivityLoginBinding binding;
    private CallbackManager manager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        loginByFacebook();
        binding.btnLoginFb.setOnClickListener(view -> loginButton.performClick());

    }

    private void loginByFacebook() {
        manager = CallbackManager.Factory.create();
        loginButton = binding.loginButton;
        loginButton.setPermissions("email", "public_profile");
        loginButton.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    try {
                        URL profile_pic = new URL(
                                "http://graph.facebook.com/" + object.getString("id") + "/picture?type=large");
                        Log.i("profile_pic",
                                profile_pic + "");
                        handleFacebookAccessToken(loginResult.getAccessToken());

                    } catch (MalformedURLException | JSONException e) {
                        e.printStackTrace();
                    }
                });
                request.executeAsync();
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
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) changeLoginActivityToMainActivity();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                changeLoginActivityToMainActivity();
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }

        });
    }

    private void changeLoginActivityToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.PhoneBuilder().build()))
                .setLogo(R.mipmap.ic_launcher)
                .build();
        startActivityForResult(intent, RC_SIGN_IN);
    }

}