package it.rokettoapp.roketto.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterAstro;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.EventViewModel;

public class FragmentHome extends Fragment {

    private EventViewModel mEventViewModel;
    private AstronautViewModel mAstroViewModel;
    private List<Event> mEvents;
    private List<Astronaut> mAstros;

    public FragmentHome() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mEventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        mAstroViewModel = new ViewModelProvider(requireActivity()).get(AstronautViewModel.class);

        if (mEvents == null) mEvents = new ArrayList<>();
        if(mAstros == null) mAstros = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        //mBtnSeeMore = rootView.findViewById(R.id.seeMoreButton);

        SwipeRefreshLayout mSwipe = rootView.findViewById(R.id.swipeHome);

        RecyclerView mRecyclerViewEvents = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        RecyclerView mRecyclerViewAstro = (RecyclerView) rootView.findViewById(R.id.rvAstronauts);

        LinearLayoutManager mLineaLayoutEvents = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewEvents.setLayoutManager(mLineaLayoutEvents);

        LinearLayoutManager mLineaLayoutAstro = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewAstro.setLayoutManager(mLineaLayoutAstro);

        RecyclerViewAdapterEvents mAdapterEvents = new RecyclerViewAdapterEvents(getContext(), mEvents, true);
        mRecyclerViewEvents.setAdapter(mAdapterEvents);

        RecyclerViewAdapterAstro mAdapterAstro = new RecyclerViewAdapterAstro(getContext(), mAstros, false);
        mRecyclerViewAstro.setAdapter(mAdapterAstro);

        mEventViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {

            mEvents.clear();
            if (!response.isError()) {
                mEvents.addAll(response.getResults());
                mAdapterEvents.notifyDataSetChanged();
                mSwipe.setRefreshing(false);
            } else {
                showError(response.getMessage());
            }
        });
        mEventViewModel.getEvents(isConnected());

        mAstroViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {
            mAstros.clear();
            if (!response.isError()) {
                mAstros.addAll(response.getResults());
                mAstros.add(null);
                mAstroViewModel.setLoading(false);
                mAdapterAstro.notifyDataSetChanged();
                mSwipe.setRefreshing(false);
            } else {
                showError(response.getMessage());
            }
        });
        mAstroViewModel.getAstronauts(isConnected());


        Button seeAllEvents = rootView.findViewById(R.id.events_see_all);
        seeAllEvents.setOnClickListener(v ->
                requireActivity().startActivity(
                        new Intent(requireActivity(),SeeAllEventsActivity.class)));

        mRecyclerViewAstro.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (mAstros.size() < 100) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollHorizontally(1) && !mAstroViewModel.isLoading()) {
                        if (mAstroViewModel.getLiveData().getValue() != null && isConnected()) {
                            mAstroViewModel.setLoading(true);
                            mAstroViewModel.getNewAstronauts();
                        } else {
                            if (!isConnected()) {
                                Context context = getContext();
                                CharSequence text = getString(R.string.connection_error);
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    }
                }
            }
        });

        mSwipe.setOnRefreshListener(() -> {
            mEventViewModel.getEvents(isConnected());
            mAstroViewModel.refreshAstronauts();
        });

        return rootView;
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showError(String errorMessage) {

        Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}
