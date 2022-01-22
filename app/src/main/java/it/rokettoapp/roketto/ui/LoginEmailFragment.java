package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.ui.viewmodel.UserViewModel;

public class LoginEmailFragment extends Fragment {

    private UserViewModel mUserViewModel;
    private String mEmail;
    private String mPassword;

    public LoginEmailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_email, container, false);

        mUserViewModel.signInWithEmail(mEmail, mPassword)
                .observe(getViewLifecycleOwner(), authenticationResponse -> {

                    if (authenticationResponse != null) {
                        if (authenticationResponse.isSuccess()) {
                            NavHostFragment.findNavController(LoginEmailFragment.this).
                                    navigate(R.id.action_loginEmailFragment_to_mainActivity);
                        } else {
                            updateUIForFailure(authenticationResponse.getMessage());
                        }
                    }
                });

        final EditText editTextEmail = view.findViewById(R.id.editEmail);
        final EditText editTextPassword = view.findViewById(R.id.editPassword);

        final Button buttonSignIn = view.findViewById(R.id.btnSigninEmail);
        buttonSignIn.setOnClickListener(item -> {

            mEmail = editTextEmail.getText().toString();
            mPassword = editTextPassword.getText().toString();
            mUserViewModel.signInWithEmail(mEmail, mPassword);
        });

        TextView textViewLinkSignUp = view.findViewById(R.id.linkSignup2);
        textViewLinkSignUp.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_loginEmailFragment_to_registerFragment));

        return view;
    }

    private void updateUIForFailure(String message) {

        Snackbar.make(requireActivity().findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();
        mUserViewModel.clear();
    }
}