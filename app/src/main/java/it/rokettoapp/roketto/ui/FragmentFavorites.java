package it.rokettoapp.roketto.ui;

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
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;

public class FragmentFavorites extends Fragment {

    private AstronautViewModel mAstronautViewModel;
    private AgencyViewModel mAgencyViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAstronautViewModel = new ViewModelProvider(requireActivity()).get(AstronautViewModel.class);
        mAgencyViewModel = new ViewModelProvider(requireActivity()).get(AgencyViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_favorites, container, false);

        TextView textView3 = rootView.findViewById(R.id.textView3);
        TextView textView4 = rootView.findViewById(R.id.textView4);

        mAstronautViewModel.getAstronauts().observe(getViewLifecycleOwner(), astronautList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Astronaut astronaut : astronautList) {
                stringBuilder.append(astronaut.getName()).append("\n");
            }
            textView3.append(stringBuilder.toString());
            Log.d("AstronautObserver", "test");
        });

        mAgencyViewModel.getAgencies().observe(getViewLifecycleOwner(), agencyList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Agency agency : agencyList) {
                stringBuilder.append(agency.getName()).append("\n");
            }
            textView4.append(stringBuilder.toString());
            Log.d("AgencyObserver", "test");
        });

        Button button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(view -> {

            textView3.setText("");
            textView4.setText("");
            mAstronautViewModel.refreshAstronauts();
            mAgencyViewModel.refreshAgencies();
        });

        return rootView;
    }
}
