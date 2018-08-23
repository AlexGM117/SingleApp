package com.softhink.single.registro.view;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.softhink.single.BaseFragment;
import com.softhink.single.R;
import com.softhink.single.dashboard.MainContainer;
import com.softhink.single.onboarding.OnboardingActivity;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroTresFragment extends BaseFragment implements View.OnClickListener, BaseFragment.onOptionsSelected {

    private ImageView btnBack;
    private ImageView imagePreview;
    private Button btnEnviarRegistro;
    private FrameLayout imageSelector;

    private final int GALLERY = 0, CAMERA = 1;

    public RegistroTresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_tres, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageSelector = view.findViewById(R.id.selectPhoto);
        imagePreview = view.findViewById(R.id.imagePreview);
        btnBack = view.findViewById(R.id.btnPrevious);
        btnEnviarRegistro = view.findViewById(R.id.btnSendReg);

        imageSelector.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnEnviarRegistro.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GALLERY:
                if (data != null){
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                        imagePreview.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case CAMERA:
                if (data != null) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imagePreview.setImageBitmap(thumbnail);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()){
            case R.id.btnPrevious:
                fragmentManager.popBackStack();
                break;

            case R.id.btnSendReg:
                startActivity(new Intent(getActivity(), OnboardingActivity.class));
                getActivity().finish();
                break;

            case R.id.selectPhoto:
                showImagePickerDialog(this);
                break;
        }
    }

    @Override
    public void fromGalery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    public void fromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
}
