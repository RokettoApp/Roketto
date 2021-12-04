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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.viewmodel.EventViewModel;

public class FragmentHome extends Fragment {

    private EventViewModel mEventViewModel;
    private List<Event> mEvents;
//    private Button mBtnSeeMore;

    public FragmentHome() {
    }

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

        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
//        mBtnSeeMore = rootView.findViewById(R.id.seeMoreButton);
        RecyclerView myrv = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        LinearLayoutManager llm =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        myrv.setLayoutManager(llm);
        RecyclerViewAdapterEvents myAdapter = new RecyclerViewAdapterEvents(getContext(), mEvents);
        myrv.setAdapter(myAdapter);

        mEventViewModel.getEvents().observe(getViewLifecycleOwner(), eventList -> {

            mEvents.clear();
            mEvents.addAll(eventList);
            myAdapter.notifyDataSetChanged();
            Log.d("FragmentHome", "test");
        });

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

        Button button = rootView.findViewById(R.id.button4);
        button.setOnClickListener(view -> mEventViewModel.refreshEvents());

        return rootView;
    }
}
