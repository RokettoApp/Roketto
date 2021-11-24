package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.spaceEvents.SpaceEvents;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.MyViewHolderEvents> {

    private List<SpaceEvents> mEvents;
    private Context mContext;

    public RecyclerViewAdapterEvents(Context mContext, List<SpaceEvents> mEvents) {
        this.mContext = mContext;
        this.mEvents = mEvents;
    }

    @Override
    public MyViewHolderEvents onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycleview_row_layout,parent,false);
        return new MyViewHolderEvents(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderEvents holder, final int position) {

        holder.event_title.setText(mEvents.get(position).getTitle());
        holder.event_d.setText(mEvents.get(position).getDescription());
        holder.eventDate.setText(mEvents.get(position).getDepartureDate());
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public static class MyViewHolderEvents extends RecyclerView.ViewHolder {

        public TextView event_title;
        public TextView event_d;
        public TextView eventDate;
        CardView cardView;

        public MyViewHolderEvents(View itemView) {
            super(itemView);
            event_title = (TextView) itemView.findViewById(R.id.event_title);
            event_d = (TextView) itemView.findViewById(R.id.event_d);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            cardView = (CardView) itemView.findViewById(R.id.event_card);
        }
    }


}
