package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
//    private Button mBtnSeeMore;

    public FragmentHome() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        /*mAstros = new ArrayList<>();

        mAstros.add(new Astronaut("Khalil", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
        mAstros.add(new Astronaut("Deiv", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
        mAstros.add(new Astronaut("Samuele", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
        mAstros.add(new Astronaut("Lorenzo", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));*/
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

        RecyclerView mRecyclerViewEvents = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        RecyclerView mRecyclerViewAstro = (RecyclerView) rootView.findViewById(R.id.rvAstronauts);

        LinearLayoutManager mLineaLayoutEvents = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewEvents.setLayoutManager(mLineaLayoutEvents);

        LinearLayoutManager mLineaLayoutAstro = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewAstro.setLayoutManager(mLineaLayoutAstro);

        RecyclerViewAdapterEvents mAdapterEvents = new RecyclerViewAdapterEvents(getContext(), mEvents, true);
        mRecyclerViewEvents.setAdapter(mAdapterEvents);

        RecyclerViewAdapterAstro mAdapterAstro = new RecyclerViewAdapterAstro(getContext(), mAstros);
        mRecyclerViewAstro.setAdapter(mAdapterAstro);

        mEventViewModel.getEvents().observe(getViewLifecycleOwner(), eventList -> {

            mEvents.clear();
            mEvents.addAll(eventList);
            mAdapterEvents.notifyDataSetChanged();
            Log.d("FragmentHome", "test");
        });

        mAstroViewModel.getAstronauts().observe(getViewLifecycleOwner(), astronauts -> {
            mAstros.clear();
            mAstros.addAll(astronauts);
            mAdapterAstro.notifyDataSetChanged();
        });

        /*
        mBtnSeeMore.setVisibility(View.VISIBLE);
        mRecyclerViewEvents.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.i("Pos",llm.findFirstVisibleItemPosition() + "");
                if (llm.findFirstVisibleItemPosition() == 0) {
                    mBtnSeeMore.setVisibility(View.GONE);
                }
                else {
                    mBtnSeeMore.setVisibility(View.VISIBLE);

                }
            }
        });*/

        Button button = rootView.findViewById(R.id.button4);
        button.setOnClickListener(view -> {
            mEventViewModel.refreshEvents();
            mAstroViewModel.refreshAstronauts();
        });

        return rootView;
    }
}
