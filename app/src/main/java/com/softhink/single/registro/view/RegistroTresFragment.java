package com.softhink.single.registro.view;


import android.Manifest;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.softhink.single.BaseFragment;
import com.softhink.single.DialogCallBack;
import com.softhink.single.R;
import com.softhink.single.registro.presenter.RegistroContract;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroTresFragment extends BaseFragment implements View.OnClickListener,
        BaseFragment.OnOptionsSelected,
        DialogCallBack {

    private ImageView btnBack;
    private ImageView imagePreview;
    private Button btnEnviarRegistro;
    private FrameLayout imageSelector;

    private RegistroContract.PhotoProfileContract.CallbackPhoto callback;
    private final int GALLERY = 0, CAMERA = 1;
    private final int PERMISSION_REQUEST_READ_STORAGE = 1;

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

        if (savedInstanceState == null) {
            imageSelector = view.findViewById(R.id.selectPhoto);
            imagePreview = view.findViewById(R.id.imagePreview);
            btnBack = view.findViewById(R.id.btnPrevious);
            btnEnviarRegistro = view.findViewById(R.id.btnSendForm);

            imageSelector.setOnClickListener(this);
            btnBack.setOnClickListener(this);
            btnEnviarRegistro.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (RegistroContract.PhotoProfileContract.CallbackPhoto) context;
        } catch (ClassCastException e){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_READ_STORAGE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    showImagePickerDialog(this);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

            case R.id.btnSendForm:
                callback.callService(null);
                break;

            case R.id.selectPhoto:
                if (checkPermissions()) {
                    showImagePickerDialog(this);
                } else {
                    showMessageDialogGalery(this);
                }
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

    @Override
    public void onAccept() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA},
                PERMISSION_REQUEST_READ_STORAGE);
    }

    @Override
    public void onCancel() {

    }
}
