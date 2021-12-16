package it.rokettoapp.roketto.ui;

import android.os.Bundle;
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
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.ui.viewmodel.ProgramViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpacecraftViewModel;

public class FragmentSettings extends Fragment {

    private SpacecraftViewModel mSpacecraftViewModel;
    private ProgramViewModel mProgramViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mSpacecraftViewModel = new ViewModelProvider(requireActivity())
                .get(SpacecraftViewModel.class);
        mProgramViewModel = new ViewModelProvider(requireActivity()).get(ProgramViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_settings,container, false);

        TextView textView5 = rootView.findViewById(R.id.textView5);
        TextView textView6 = rootView.findViewById(R.id.textView6);

        mSpacecraftViewModel.getSpacecrafts().observe(getViewLifecycleOwner(), spacecraftList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Spacecraft spacecraft : spacecraftList) {
                stringBuilder.append(spacecraft.getName()).append("\n");
            }
            textView5.append(stringBuilder.toString());
        });

        mProgramViewModel.getProgramById(17).observe(getViewLifecycleOwner(), programList -> {

            textView6.append(programList.get(0).getName());
        });

        Button button = rootView.findViewById(R.id.button3);
        button.setOnClickListener(view -> {

            textView5.setText("");
            textView6.setText("");
            mSpacecraftViewModel.refreshSpacecrafts();
            mProgramViewModel.refreshPrograms();
        });

        return rootView;
    }
}
