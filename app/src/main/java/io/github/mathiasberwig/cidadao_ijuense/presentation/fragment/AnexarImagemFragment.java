package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mathiasberwig.cidadao_ijuense.R;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class AnexarImagemFragment extends Fragment {
    private static final String TAG = "AnexarImagemFragment";

    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int CAMERA_IMAGE_REQUEST = 2;

    private OnImagemAnexadaListener listener;

    public static AnexarImagemFragment newInstance() {
        return new AnexarImagemFragment();
    }

    public AnexarImagemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anexar_imagem, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnImagemAnexadaListener) {
            listener = (OnImagemAnexadaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnImagemAnexadaListener");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listener.onImagemAnexada(null);
    }

    @OnClick(R.id.btn_camera)
    public void abrirCamera() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                EasyImage.openCamera(getActivity(), CAMERA_IMAGE_REQUEST);
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                Toast.makeText(getContext(), R.string.error_permission_camera, Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @OnClick(R.id.btn_galeria)
    public void abrirGaleria() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                EasyImage.openGallery(getActivity(), GALLERY_IMAGE_REQUEST);
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                Toast.makeText(getContext(), R.string.error_permission_camera, Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @OnClick(R.id.btn_nao_anexar_imagem)
    public void naoAnexarImagem() {
        listener.onImagemAnexada(null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnImagemAnexadaListener {
        void onImagemAnexada(Uri uri);
    }
}
