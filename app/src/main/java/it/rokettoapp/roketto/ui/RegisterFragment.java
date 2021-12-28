package it.rokettoapp.roketto.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.ui.viewmodel.UserViewModel;

public class RegisterFragment extends Fragment {

    private UserViewModel mUserViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText editTextEmail = view.findViewById(R.id.email);
        final EditText editTextPassword = view.findViewById(R.id.password);

        final Button buttonRegister = view.findViewById(R.id.register_button);
        buttonRegister.setOnClickListener(view1 -> {

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            mUserViewModel.signUpWithEmail(email, password)
                    .observe(getViewLifecycleOwner(), authenticationResponse -> {

                if (authenticationResponse != null) {
                    if (authenticationResponse.isSuccess()) {
                        Navigation.findNavController(view1).navigate(R.id.mainActivity);
                    } else {
                        updateUIForFailure(authenticationResponse.getMessage());
                    }
                }
            });
        });

        return view;
    }

    private void updateUIForFailure(String message) {

        Snackbar.make(requireActivity().findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();
        mUserViewModel.clear();
    }
}