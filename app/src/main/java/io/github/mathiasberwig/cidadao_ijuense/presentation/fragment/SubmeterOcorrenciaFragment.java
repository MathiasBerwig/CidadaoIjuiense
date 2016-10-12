package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.github.mathiasberwig.cidadao_ijuense.R;

public class SubmeterOcorrenciaFragment extends Fragment {
    private static final String TAG = "SubmeterOcorrenciaFragm";

    // Views
    @BindView(R.id.input_nome_usuario) EditText inputNomeUsuario;
    @BindView(R.id.input_telefone_usuario) EditText inputTelefoneUsuario;
    @BindView(R.id.input_email_usuario) EditText inputEmailUsuario;
    @BindView(R.id.cb_ocorrencia_anonima) CheckBox cbOcorrenciaAnonima;
    @BindView(R.id.cb_receber_feedback) CheckBox cbReceberFeedback;

    private OnSubmeterOcorrenciaListener listener;

    public static SubmeterOcorrenciaFragment newInstance() {
        return new SubmeterOcorrenciaFragment();
    }

    public SubmeterOcorrenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submeter_ocorrencia, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubmeterOcorrenciaListener) {
            listener = (OnSubmeterOcorrenciaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSubmeterOcorrenciaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnCheckedChanged(R.id.cb_ocorrencia_anonima)
    void onOcorrenciaAnonimaChecked(boolean checked) {
        inputNomeUsuario.setEnabled(!checked);
        inputEmailUsuario.setEnabled(!checked);
        inputTelefoneUsuario.setEnabled(!checked);
        cbReceberFeedback.setEnabled(!checked);

        if (checked) {
            inputNomeUsuario.setText(null);
            inputEmailUsuario.setText(null);
            inputTelefoneUsuario.setText(null);
            cbReceberFeedback.setChecked(false);
        }
    }

    public interface OnSubmeterOcorrenciaListener {
        void onOcorrenciaSubmetida(String nome, String telefone, boolean feedback);
    }
}
