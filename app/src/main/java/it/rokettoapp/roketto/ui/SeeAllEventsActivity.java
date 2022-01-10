package it.rokettoapp.roketto.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.viewmodel.EventViewModel;

public class SeeAllEventsActivity extends AppCompatActivity {

    private EventViewModel mEventViewModel;
    private List<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_events);
        getSupportActionBar().hide();

        SwipeRefreshLayout mSwipe = findViewById(R.id.swipeEvents);

        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        if (mEvents == null) mEvents = new ArrayList<>();

        RecyclerView mRecyclerViewEvents = (RecyclerView) findViewById(R.id.rvAllEvents);
        LinearLayoutManager mLineaLayoutEvents = new LinearLayoutManager(this);
        mLineaLayoutEvents.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewEvents.setLayoutManager(mLineaLayoutEvents);

        RecyclerViewAdapterEvents mAdapterEvents = new RecyclerViewAdapterEvents(this, mEvents, false);
        mRecyclerViewEvents.setAdapter(mAdapterEvents);

        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backSeeAllEvents);
        back.setOnClickListener(v -> onBackPressed());

        mEventViewModel.getLiveData().observe(this, response -> {

            mEvents.clear();
            if (!response.isError()) {
                mEvents.addAll(response.getResults());
                if(mEvents.size() < 100)
                    mEvents.add(null);
                mAdapterEvents.notifyDataSetChanged();
                mEventViewModel.setLoading(false);
                mSwipe.setRefreshing(false);
            } else {
                showError(response.getMessage());
            }
        });
        mEventViewModel.getEvents(isConnected());

        mRecyclerViewEvents.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (mEvents.size() < 100) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(1) && !mEventViewModel.isLoading()) {
                        if (mEventViewModel.getLiveData().getValue() != null) {
                            Log.d("BlogPostObserver", "test3");
                            mEventViewModel.setLoading(true);
                            mEventViewModel.getNewEvents();
                        }
                    }
                }
            }
        });

        mSwipe.setOnRefreshListener(() -> mEventViewModel.refreshEvents());
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showError(String errorMessage) {

        Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}