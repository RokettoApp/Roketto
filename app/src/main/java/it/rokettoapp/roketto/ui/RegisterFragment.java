package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

        final EditText editTextEmail = view.findViewById(R.id.editEmail);
        final EditText editTextPassword = view.findViewById(R.id.editPassword);
        final EditText editTextConfirmPassword = view.findViewById(R.id.editConfirmPassword);

        final Button buttonRegister = view.findViewById(R.id.btnSigninEmail);
        buttonRegister.setOnClickListener(view1 -> {

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();
            if (password.equals(confirmPassword)) {
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
            } else {
                showError(getString(R.string.password_error));
            }
        });

        final TextView textViewLogIn = view.findViewById(R.id.linkSignup2);
        textViewLogIn.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_registerFragment_to_loginFragment));

        return view;
    }

    private void updateUIForFailure(String message) {

        Snackbar.make(requireActivity().findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();
        mUserViewModel.clear();
    }

    private void showError(String errorMessage) {

        Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}