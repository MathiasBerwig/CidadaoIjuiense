package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mathiasberwig.cidadao_ijuense.R;
import io.github.mathiasberwig.cidadao_ijuense.presentation.adapter.CardPagerAdapter;
import io.github.mathiasberwig.cidadao_ijuense.presentation.adapter.CardPagerAdapter.OcorrenciaClickListener;
import io.github.mathiasberwig.cidadao_ijuense.presentation.transformer.ShadowTransformer;

public class SelecionarCategoriaFragment extends Fragment {
    private static final String TAG = "SelecionarCategoriaFrag";

    // Views
    @BindView(R.id.view_pager) ViewPager viewPager;

    private CardPagerAdapter cardAdapter;
    private ShadowTransformer cardShadowTransformer;
    private OcorrenciaClickListener listener;

    public static SelecionarCategoriaFragment newInstance() {
        return new SelecionarCategoriaFragment();
    }

    public SelecionarCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selecionar_categoria, container, false);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.tv_titulo_selecione_a).bringToFront();;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCards();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OcorrenciaClickListener) {
            listener = (OcorrenciaClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OcorrenciaClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void setupCards() {
        cardAdapter = new CardPagerAdapter(listener);
        cardShadowTransformer = new ShadowTransformer(viewPager, cardAdapter);
        cardShadowTransformer.enableScaling(true);
        viewPager.setAdapter(cardAdapter);
        viewPager.setPageTransformer(false, cardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
    }
}
