package com.fivelove.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.login.LoginManager;
import com.fivelove.databinding.ActivityProfileBinding;
import com.fivelove.utils.Constant;
import com.fivelove.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileActivity extends BaseActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ActivityProfileBinding binding;
    private Uri imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        setViewModel();
        binding.btnLogout.setOnClickListener(view -> signOut());

        binding.btnSave.setOnClickListener(view -> {
            if (binding.edtName.getText().toString().isEmpty() || binding.edtPhone.getText().toString().isEmpty()) {
                return;
            }
            updateDetails(binding.edtName.getText().toString().trim(), String.valueOf(imgUrl));
        });
        binding.avtProfile.setOnClickListener(view -> chooseImage());
    }

    public void setViewModel() {
        final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if (userViewModel.getCurrentUser() == null) {
            return;
        }
        userViewModel.getCurrentUser().observe(this, users -> {
                    binding.setUser(users);
                }
        );
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Constant.IMAGE);
    }

    public String getExtensionImage(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    public void uploadImage(Uri imgUri) {
        final float idImage = System.currentTimeMillis();
        Constant.FIREBASE_STORAGE.child(idImage + "." + getExtensionImage(imgUri))
                .putFile(imgUri)
                .addOnSuccessListener(taskSnapshot -> {
                    getImageFromUrl(idImage + "." + getExtensionImage(imgUri));
                });
    }


    private void updateDetails(String displayName, String profileImageUrl) {
        UserProfileChangeRequest updateRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .setPhotoUri(Uri.parse(profileImageUrl))
                .build();

        user = mAuth.getCurrentUser();
        Objects.requireNonNull(user).updateProfile(updateRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //completed
                            Toast.makeText(ProfileActivity.this, "Changes saved.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, user.getDisplayName()+user.getPhotoUrl());
                            finish();
                        }
                    }
                });

    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void getImageFromUrl(String idImage) {
        StorageReference storageImage = Constant.FIREBASE_STORAGE.child(idImage);
        storageImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(binding.avtProfile);
                imgUrl = uri;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imgUri = data.getData();
            binding.avtProfile.setImageURI(imgUri);
            uploadImage(imgUri);
        }
    }
}