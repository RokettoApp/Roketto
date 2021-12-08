package it.rokettoapp.roketto.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.spaceEvents.SpaceEvents;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        SpaceEvents mEvent = (SpaceEvents) getIntent().getSerializableExtra("Event");

        TextView eventDetailName = findViewById(R.id.eventDetail_name);
        eventDetailName.setText(mEvent.getTitle());

    }
}