package com.example.quanlyuserstrenfirebase.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.quanlyuserstrenfirebase.MainActivity;
import com.example.quanlyuserstrenfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.quanlyuserstrenfirebase.MainActivity.MY_REQUEST_CODE;


public class MyProfileFragment extends Fragment {
    private View mView;
    private ImageView imgAvatar;
    private EditText edtFullName,edtEmail;
    private Button btnUpdateProfile, btnUpdateEmail;
    Uri mUri;
    private  MainActivity mMainActivity;
    private ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       dialog=new ProgressDialog(getActivity());
        mView= inflater.inflate(R.layout.fragment_my_profile, container, false);
        initUi();
        mMainActivity= (MainActivity) getActivity();
        setUserInformation();
        initListener();
        return mView;
    }

    private void initUi(){
        imgAvatar=mView.findViewById(R.id.img_avatar);
        edtFullName=mView.findViewById(R.id.edt_full_name);
        edtEmail=mView.findViewById(R.id.edt_email);
        btnUpdateProfile=mView.findViewById(R.id.btn_update_profile);
        btnUpdateEmail = mView.findViewById(R.id.btn_update_email);
    }
    private void setUserInformation() {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        else
        {
            edtFullName.setText(user.getDisplayName());
            edtEmail.setText(user.getEmail());
            Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.ic_my_profile).into(imgAvatar);

        }
    }
    private void initListener() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdatePrifile();
            }
        });
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateEmail();
            }
        });
    }

    private void onClickRequestPermission() {

        if(mMainActivity==null){
            return;
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mMainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mMainActivity.openGallery();
        }else{
            String [] permission={Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    public void setBitmapImageview(Bitmap bitmap){
        imgAvatar.setImageBitmap(bitmap);
    }
    private void onClickUpdatePrifile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        dialog.show();
        String fullName=edtFullName.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullName)
                .setPhotoUri(mUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(),"Update Profile Success",Toast.LENGTH_SHORT).show();
                            mMainActivity.showUserInformation();
                        }
                    }
                });
    }
    private void onClickUpdateEmail(){
        String strNewEmail = edtEmail.getText().toString().trim();
        dialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(strNewEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(),"User mail address updated.",Toast.LENGTH_SHORT).show();
                            mMainActivity.showUserInformation();
                        }
                    }
                });

    }
}