package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mathiasberwig.cidadao_ijuense.R;

public class ProtocoloFragment extends Fragment {
    private static final String TAG = "ProtocoloFragment";

    private boolean feedback;
    private boolean loading = true;

    // Views
    @BindView(R.id.tv_status_protocolo) TextView tvStatusProtocolo;
    @BindView(R.id.tv_orientacao_protocolo) TextView tvOrientacaoProtocolo;
    @BindView(R.id.view_switcher) ViewSwitcher viewSwitcher;
    @BindView(R.id.btn_sair) Button btnSair;
    @BindView(R.id.btn_nova_solicitacao) Button btnNovaSolicitacao;

    private OnProtocoloRecebidoListener listener;

    public ProtocoloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        feedback = args.getBoolean("feedback");
    }

    public static ProtocoloFragment newInstance(boolean feedback) {
        ProtocoloFragment fragment = new ProtocoloFragment();
        Bundle args = new Bundle();
        args.putBoolean("feedback", feedback);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protocolo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loading) mostrarSucesso();
            }
        }, 4000);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProtocoloRecebidoListener) {
            listener = (OnProtocoloRecebidoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProtocoloRecebidoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_nova_solicitacao)
    public void novaSolicitacao() {
        restaurarEstado();
        listener.onProtocoloRecebido(false);
    }

    @OnClick(R.id.btn_sair)
    public void sair() {
        listener.onProtocoloRecebido(true);
    }

    private void mostrarSucesso() {
        loading = false;
        viewSwitcher.showNext();
        tvStatusProtocolo.setText(R.string.text_protocolo_enviado);
        tvOrientacaoProtocolo.setText(feedback ?
                R.string.text_orientacao_protocolo_feedback :
                R.string.text_orientacao_protocolo_sem_feedback);
    }

    private void restaurarEstado() {
        viewSwitcher.showPrevious();
        tvStatusProtocolo.setText(R.string.text_enviando_protocolo);
    }

    public interface OnProtocoloRecebidoListener {
        void onProtocoloRecebido(boolean fecharApp);
    }
}
