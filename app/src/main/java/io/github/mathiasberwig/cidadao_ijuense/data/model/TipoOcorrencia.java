package io.github.mathiasberwig.cidadao_ijuense.data.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import io.github.mathiasberwig.cidadao_ijuense.R;

/**
 * Created by mathias on 10/10/16.
 */
public enum TipoOcorrencia {

    SINALIZACAO_E_PLACAS(R.string.ocor_tit_sinalizacao_e_placas, R.drawable.sinalizacao_e_placas),
    DENUNCIAS_OU_RECLAMACOES(R.string.ocor_tit_denuncias_ou_reclamacoes, R.drawable.denuncias_ou_reclamacoes),
    ILUMINACAO_PUBLICA(R.string.ocor_tit_iluminacao_publica, R.drawable.iluminacao_publica),
    VIAS_PUBLICAS_E_TRANSITO(R.string.ocor_tit_vias_publicas_e_transito, R.drawable.vias_publicas_e_transito),
    LIMPEZA_URBANA(R.string.ocor_tit_limpeza_urbana, R.drawable.limpeza_urbana);

    public int titulo;
    public int icone;

    TipoOcorrencia(@StringRes int titulo, @DrawableRes int icone) {
        this.titulo = titulo;
        this.icone = icone;
    }
}
