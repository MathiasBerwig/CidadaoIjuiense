package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.mathiasberwig.cidadao_ijuense.R;

public class SelecionarLocalFragment extends Fragment {
    private static final String TAG = "SelecionarLocalFragment";

    public static SelecionarLocalFragment newInstance() {
        return new SelecionarLocalFragment();
    }

    public SelecionarLocalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selecionar_local, container, false);
    }
}
