package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mathiasberwig.cidadao_ijuense.R;

public class AnexarImagemFragment extends Fragment {
    private static final String TAG = "AnexarImagemFragment";

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

    @OnClick(R.id.btn_camera)
    public void abrirCamera() {

    }

    @OnClick(R.id.btn_galeria)
    public void abrirGaleria() {

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
