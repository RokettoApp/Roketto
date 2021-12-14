package it.rokettoapp.roketto.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Event;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        getSupportActionBar().hide();

        Event mEvent = (Event) getIntent().getSerializableExtra("Event");
        ImageButton back = (ImageButton)findViewById(R.id.backButtonEvent);
        TextView eventDescription = (TextView) findViewById(R.id.eventDetailDescription);
        ImageView mEventImage = (ImageView) findViewById(R.id.imageEvent);

        Glide.with(this).load(mEvent.getFeatureImage()).into(mEventImage);

        back.setOnClickListener(v -> onBackPressed());

        eventDescription.setText(mEvent.getDescription());

    }
}