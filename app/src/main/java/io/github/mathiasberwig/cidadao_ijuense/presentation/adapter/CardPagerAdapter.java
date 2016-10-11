package io.github.mathiasberwig.cidadao_ijuense.presentation.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.mathiasberwig.cidadao_ijuense.R;
import io.github.mathiasberwig.cidadao_ijuense.data.model.TipoOcorrencia;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<TipoOcorrencia> mData;
    private float mBaseElevation;
    private OcorrenciaClickListener mListener;

    public CardPagerAdapter(OcorrenciaClickListener listener) {
        mListener = listener;
        mData = new ArrayList<>();
        mViews = new ArrayList<>();

        mData.add(TipoOcorrencia.SINALIZACAO_E_PLACAS);
        mData.add(TipoOcorrencia.VIAS_PUBLICAS_E_TRANSITO);
        mData.add(TipoOcorrencia.ILUMINACAO_PUBLICA);
        mData.add(TipoOcorrencia.LIMPEZA_URBANA);
        mData.add(TipoOcorrencia.DENUNCIAS_OU_RECLAMACOES);

        for (int i = 0; i < 5; i++) {
            mViews.add(null);
        }
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_item_ocorrencia, container, false);
        container.addView(view);

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);
        final ImageView imageView = (ImageView) cardView.findViewById(R.id.img_icone_ocorrencia);
        final TextView textView = (TextView) cardView.findViewById(R.id.tv_titulo_ocorrencia);
        final TipoOcorrencia to = mData.get(position);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOcorrenciaClick(to);
            }
        });

        imageView.setImageResource(to.icone);
        textView.setText(to.titulo);

        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    public interface OcorrenciaClickListener {
        void onOcorrenciaClick(TipoOcorrencia ocorrencia);
    }
}
