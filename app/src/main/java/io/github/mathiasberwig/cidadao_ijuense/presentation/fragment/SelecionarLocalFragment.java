package io.github.mathiasberwig.cidadao_ijuense.presentation.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mathiasberwig.cidadao_ijuense.R;

public class SelecionarLocalFragment extends Fragment {
    private static final String TAG = "SelecionarLocalFragment";

    private OnLocalSelecionadoListener listener;

    /**
     * Request code passed to the PlacePicker intent to identify its result when it returns.
     */
    private static final int REQUEST_PLACE_PICKER = 1;

    // Views
    @BindView(R.id.btn_informar_local) Button btnInformarLocal;

    public static SelecionarLocalFragment newInstance() {
        return new SelecionarLocalFragment();
    }

    public SelecionarLocalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selecionar_local, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                /* User has picked a place, extract data.
                   Data is extracted from the returned intent by retrieving a Place object from
                   the PlacePicker.
                 */
                final Place place = PlacePicker.getPlace(data, getActivity());

                // Print data to debug log
                Log.d(TAG, "Place selected: " + place.getId() + " (" + place.getAddress().toString() + ")");

                // Envia o local selecionado à activity
                listener.onLocalSelecionado(place);
            } else {
                // User has not selected a place
                Toast.makeText(getContext(), R.string.toast_selecione_local, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLocalSelecionadoListener) {
            listener = (OnLocalSelecionadoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLocalSelecionadoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_informar_local)
    public void mostrarSeletorLocal () {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        Intent intent = null;
        try {
            intent = intentBuilder.build(getActivity());
        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), getActivity(), 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(getActivity(), "Google Play Services não está disponível.",
                    Toast.LENGTH_LONG).show();
        }
        // Start the Intent by requesting a result, identified by a request code.
        startActivityForResult(intent, REQUEST_PLACE_PICKER);
    }

    public interface OnLocalSelecionadoListener {
        void onLocalSelecionado(Place place);
    }
}
