package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mathiasberwig.cidadao_ijuense.R;

public class DescreverOcorrenciaFragment extends Fragment {
    private static final String TAG = "DescreverOcorrenciaFrag";

    // Views
    @BindView(R.id.input_titulo_ocorrencia) EditText inputTituloOcorrencia;
    @BindView(R.id.input_descricao_ocorrencia) EditText inputDescricaoOcorrencia;

    private OnDescricaoCompletaListener listener;

    public DescreverOcorrenciaFragment() {
        // Required empty public constructor
    }

    public static DescreverOcorrenciaFragment newInstance() {
        return new DescreverOcorrenciaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descrever_ocorrencia, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDescricaoCompletaListener) {
            listener = (OnDescricaoCompletaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDescricaoCompletaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_pronto_descricao)
    public void definirDescricao() {
        if (verificarCampos()) {
            listener.onDescricaoCompleta(
                    inputTituloOcorrencia.getText().toString(),
                    inputTituloOcorrencia.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(), R.string.toast_preencha_corretamente, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private boolean verificarCampos() {
        return inputTituloOcorrencia.getText().toString().trim().length() != 0 ||
            inputDescricaoOcorrencia.getText().toString().trim().length() != 0;
    }

    public interface OnDescricaoCompletaListener {
        void onDescricaoCompleta(String titulo, String descricao);
    }
}
