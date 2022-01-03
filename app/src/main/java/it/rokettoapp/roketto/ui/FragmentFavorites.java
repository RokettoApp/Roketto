package it.rokettoapp.roketto.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;

public class FragmentFavorites extends Fragment {

    private AgencyViewModel mAgencyViewModel;
    int lastAgencyId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAgencyViewModel = new ViewModelProvider(requireActivity()).get(AgencyViewModel.class);
        lastAgencyId = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_favorites, container, false);

        TextView textView4 = rootView.findViewById(R.id.textView4);
        mAgencyViewModel.getLiveData().observe(getViewLifecycleOwner(), agencyList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Agency agency : agencyList) {
                stringBuilder.append(agency.getName()).append("\n");
                lastAgencyId = agency.getId();
            }
            textView4.append(stringBuilder.toString());
            Log.d("AgencyObserver", "test");
        });
        mAgencyViewModel.getAgencies(isConnected());

        Button button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(view -> {

            textView4.setText("");
            mAgencyViewModel.getNextAgencies(lastAgencyId);
        });

        return rootView;
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
