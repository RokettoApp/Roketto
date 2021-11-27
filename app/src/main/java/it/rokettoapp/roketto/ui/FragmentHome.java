package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.spaceEvents.SpaceEvents;

public class FragmentHome extends Fragment {

    private List<SpaceEvents> mEvents;
    private Button mBtnSeeMore;
    public FragmentHome() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvents = new ArrayList<>();

        mEvents.add(new SpaceEvents("Apollo #1", "Apollo 12 fu la sesta missione con equipaggio nell'ambito del programma Apollo della NASA e la seconda ad atterrare sulla Luna, dopo l'Apollo 11. Decollò dal John F. Kennedy Space Center il 14 novembre 1969 alle 16:22 UTC. Durante la missione. ", new Date(1637570235000L)));
        mEvents.add(new SpaceEvents("Apollo #2", "Apollo 12 fu la sesta missione con equipaggio nell'ambito del programma Apollo della NASA e la seconda ad atterrare sulla Luna, dopo l'Apollo 11. Decollò dal John F. Kennedy Space Center il 14 novembre 1969 alle 16:22 UTC. Durante la missione. ", new Date(1637570235000L)));
        mEvents.add(new SpaceEvents("Apollo #1", "Apollo 12 fu la sesta missione con equipaggio nell'ambito del programma Apollo della NASA e la seconda ad atterrare sulla Luna, dopo l'Apollo 11. Decollò dal John F. Kennedy Space Center il 14 novembre 1969 alle 16:22 UTC. Durante la missione. ", new Date(1637570235000L)));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        mBtnSeeMore = rootView.findViewById(R.id.seeMoreButton);
        RecyclerView myrv = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myrv.setLayoutManager(llm);

        RecyclerViewAdapterEvents myAdapter = new RecyclerViewAdapterEvents(getContext(), mEvents);
        myrv.setAdapter(myAdapter);
        /*
        mBtnSeeMore.setVisibility(View.VISIBLE);
        myrv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        return rootView;
    }
}
