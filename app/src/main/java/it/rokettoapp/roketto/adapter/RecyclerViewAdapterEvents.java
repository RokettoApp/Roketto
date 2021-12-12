package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.ui.EventDetailActivity;
import it.rokettoapp.roketto.model.Event;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.MyViewHolderEvents> {

    private List<Event> mEvents;
    private Context mContext;

    public RecyclerViewAdapterEvents(Context mContext, List<Event> mEvents) {
        this.mContext = mContext;
        this.mEvents = mEvents;
    }

    @Override
    public MyViewHolderEvents onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycleview_row_layout,parent,false);

        MyViewHolderEvents mHolder = new MyViewHolderEvents(view, new MyClickListener() {
            @Override
            public void onClick(int p) {

                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra("Event", mEvents.get(p));
                mContext.startActivity(intent);
            }
        });

        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolderEvents holder, final int position) {

        holder.event_title.setText(mEvents.get(position).getName());
        holder.event_d.setText(mEvents.get(position).getDescription());
        holder.eventDate.setText(mEvents.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public static class MyViewHolderEvents extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView event_title;
        public TextView event_d;
        public TextView eventDate;
        public Button event_button;

        CardView cardView;
        MyClickListener listener;

        public MyViewHolderEvents(View itemView, MyClickListener listener) {
            super(itemView);
            event_title = (TextView) itemView.findViewById(R.id.event_title);
            event_d = (TextView) itemView.findViewById(R.id.event_d);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            cardView = (CardView) itemView.findViewById(R.id.event_card);
            event_button = (Button) itemView.findViewById(R.id.idbutton);

            this.listener = listener;

            event_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(this.getLayoutPosition());
        }
    }

    public interface MyClickListener{
        void onClick(int p);
    }


}
