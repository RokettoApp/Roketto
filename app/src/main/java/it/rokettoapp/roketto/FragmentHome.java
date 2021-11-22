package it.rokettoapp.roketto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.spaceEvents.SpaceEvents;

public class FragmentHome extends Fragment {

    private List<SpaceEvents> mEvents;

    public FragmentHome() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvents = new ArrayList<>();
        mEvents.add(new SpaceEvents("Apollo 1", "prova 1", new Date(1637570235000L)));
        mEvents.add(new SpaceEvents("Apollo 2", "prova 2", new Date(1537570235000L)));
        mEvents.add(new SpaceEvents("Apollo 3", "prova 3", new Date(1437570235000L)));
        mEvents.add(new SpaceEvents("Apollo 4", "prova 4", new Date(1337570235000L)));
        mEvents.add(new SpaceEvents("Apollo 5", "prova 5", new Date(127570235000L)));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);

        RecyclerView myrv = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        myrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        RecyclerViewAdapterEvents myAdapter = new RecyclerViewAdapterEvents(getContext(), mEvents);
        myrv.setAdapter(myAdapter);
        return rootView;
    }
}
