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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.EventViewModel;
import it.rokettoapp.roketto.ui.viewmodel.FavouritesViewModel;

public class FragmentFavorites extends Fragment {

    private EventViewModel mEventViewModel;
    private List<Event> mEvents;
    private RecyclerViewAdapterEvents mAdapterEvents;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mEventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        if (mEvents == null) mEvents = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_favorites, container, false);

        RecyclerView mRecyclerViewEvents = (RecyclerView) rootView.findViewById(R.id.rvFavoritesEvents);

        LinearLayoutManager mLineaLayoutEvents = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewEvents.setLayoutManager(mLineaLayoutEvents);

        mAdapterEvents = new RecyclerViewAdapterEvents(getContext(), mEvents, false);
        mRecyclerViewEvents.setAdapter(mAdapterEvents);

        mEventViewModel.getLiveData().observe(getViewLifecycleOwner(), events -> {
            mEvents.clear();
            mEvents.addAll(events);
            mAdapterEvents.notifyDataSetChanged();
        });
        mEventViewModel.getFavoritesEvents();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mEventViewModel.getLiveData().observe(getViewLifecycleOwner(), events -> {
            mEvents.clear();
            mEvents.addAll(events);
            mAdapterEvents.notifyDataSetChanged();
        });
        mEventViewModel.getFavoritesEvents();
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
