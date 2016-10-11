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
import io.github.mathiasberwig.cidadao_ijuense.data.model.TipoOcorrencia;
import io.github.mathiasberwig.cidadao_ijuense.presentation.adapter.CardPagerAdapter;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.DescreverOcorrenciaFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.SelecionarCategoriaFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.fragment.SelecionarLocalFragment;
import io.github.mathiasberwig.cidadao_ijuense.presentation.view.CustomViewPager;

public class MainActivity extends AppCompatActivity
        implements CardPagerAdapter.OcorrenciaClickListener,
        SelecionarLocalFragment.OnLocalSelecionadoListener,
        DescreverOcorrenciaFragment.OnDescricaoCompletaListener {

    private static final String TAG = "MainActivity";

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
        stepperIndicator.setViewPager(viewPager);
    }

    @Override
    public void onOcorrenciaClick(TipoOcorrencia ocorrencia) {
        viewPager.setCurrentItem(1, true);
    }

    @Override
    public void onLocalSelecionado(Place place) {
        proximaPagina();
    }

    @Override
    public void onDescricaoCompleta(String titulo, String descricao) {
        proximaPagina();
    }

    private void proximaPagina() {
        final int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1, true);
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
                default:
                    // TODO: Remover após colocar todos os fragmentos
                    return SelecionarCategoriaFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
