package io.github.mathiasberwig.cidadao_ijuense.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.badoualy.stepperindicator.StepperIndicator;
import com.google.android.gms.location.places.Place;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mathiasberwig.cidadao_ijuense.R;
import io.github.mathiasberwig.cidadao_ijuense.data.model.Ocorrencia;
import io.github.mathiasberwig.cidadao_ijuense.data.model.TipoOcorrencia;
import io.github.mathiasberwig.cidadao_ijuense.presentation.adapter.CardPagerAdapter;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.DescreverOcorrenciaFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.ProtocoloFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.SelecionarCategoriaFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.SelecionarLocalFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.SubmeterOcorrenciaFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.view.CustomViewPager;

public class MainActivity extends AppCompatActivity
        implements CardPagerAdapter.OcorrenciaClickListener,
        SelecionarLocalFragment.OnLocalSelecionadoListener,
        DescreverOcorrenciaFragment.OnDescricaoCompletaListener,
        SubmeterOcorrenciaFragment.OnSubmeterOcorrenciaListener,
        ProtocoloFragment.OnProtocoloRecebidoListener {

    private static final String TAG = "MainActivity";

    private Ocorrencia ocorrencia = new Ocorrencia();

    // Views
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.container) CustomViewPager viewPager;
    @BindView(R.id.stepper_indicator) StepperIndicator stepperIndicator;

    // Instances
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
        setupViewPager();
    }

    @Override
    public void onBackPressed() {
        final int currentItem = viewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(currentItem - 1, true);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupViewPager() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setPagingEnabled(false);
        stepperIndicator.setViewPager(viewPager, true);
    }

    @Override
    public void onOcorrenciaClick(TipoOcorrencia tipoOcorrencia) {
        viewPager.setCurrentItem(1, true);
        ocorrencia.setTipo(getString(tipoOcorrencia.titulo));
    }

    @Override
    public void onLocalSelecionado(Place place) {
        proximaPagina();
        if (place != null)
            ocorrencia.setLocal(place.getAddress().toString());
    }

    @Override
    public void onDescricaoCompleta(String titulo, String descricao) {
        proximaPagina();
        ocorrencia.setTitulo(titulo);
        ocorrencia.setDescricao(descricao);
    }

    @Override
    public void onOcorrenciaSubmetida(String nome, String telefone, String email, boolean feedback) {
        proximaPagina();
        ocorrencia.setNome(nome);
        ocorrencia.setTelefone(telefone);
        ocorrencia.setEmail(email);
        ocorrencia.setFeedback(feedback);
    }

    @Override
    public void onProtocoloRecebido(boolean fecharApp) {
        if (fecharApp) finish();
        else primeiraPagina();
    }

    private void proximaPagina() {
        final int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1, true);
    }

    private void primeiraPagina() {
        viewPager.setCurrentItem(0, true);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return SelecionarCategoriaFragment.newInstance();
                case 1: return SelecionarLocalFragment.newInstance();
                case 2: return DescreverOcorrenciaFragment.newInstance();
                case 3: return SubmeterOcorrenciaFragment.newInstance();
                case 4: return ProtocoloFragment.newInstance(ocorrencia.isFeedback());
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
