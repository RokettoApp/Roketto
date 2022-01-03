package it.rokettoapp.roketto.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.ui.viewmodel.FavouritesViewModel;

public class FragmentFavorites extends Fragment {

    private FavouritesViewModel mFavouritesViewModel;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mFavouritesViewModel = new ViewModelProvider(requireActivity())
                .get(FavouritesViewModel.class);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_favorites, container, false);

        TextView textView4 = rootView.findViewById(R.id.textView4);
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            mFavouritesViewModel.readFavouriteEvents(firebaseUser.getUid())
                    .observe(getViewLifecycleOwner(), user -> {
                        StringBuilder stringBuilder = new StringBuilder();
                        if (user != null) {
                            for (Integer id : user.getFavouriteEvents()) {
                                stringBuilder.append(id);
                            }
                        }
                        textView4.setText(stringBuilder.toString());
            });
        }

        return rootView;
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
