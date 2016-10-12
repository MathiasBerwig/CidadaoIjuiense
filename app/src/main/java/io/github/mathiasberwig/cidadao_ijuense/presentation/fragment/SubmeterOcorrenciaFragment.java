package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
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

    private boolean ignoreNextOnCheck;

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
    void onOcorrenciaAnonimaChecked(final boolean checked) {
        if (ignoreNextOnCheck) {
            ignoreNextOnCheck = false;
            return;
        }

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_tit_confirmacao_anonimo)
                .setMessage(R.string.dialog_mes_confirmacao_anonimo)
                .setPositiveButton(R.string.action_sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                })
                .setNegativeButton(R.string.action_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ignoreNextOnCheck = true;
                        cbOcorrenciaAnonima.setChecked(!checked);
                    }
                })
                .show();
    }

    @OnClick(R.id.btn_submeter_ocorrencia)
    void submeterOcorrencia() {
        if (verificarCampos()) {
            listener.onOcorrenciaSubmetida(
                    inputNomeUsuario.getText().toString(),
                    inputTelefoneUsuario.getText().toString(),
                    inputEmailUsuario.getText().toString(),
                    cbReceberFeedback.isChecked());
        } else {
            Toast toast = Toast.makeText(getContext(), R.string.toast_preencha_corretamente, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private boolean verificarCampos() {
        return cbOcorrenciaAnonima.isChecked() ||
                inputNomeUsuario.getText().toString().trim().length() != 0 &&
                inputTelefoneUsuario.getText().toString().trim().length() != 0 &&
                inputEmailUsuario.getText().toString().trim().length() != 0;

    }

    public interface OnSubmeterOcorrenciaListener {
        void onOcorrenciaSubmetida(String nome, String telefone, String email, boolean feedback);
    }
}
