package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.EventDetailActivity;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.MyViewHolderEvents> {

    private List<Event> mEvents;
    private Context mContext;
    private boolean mLimit;

    public RecyclerViewAdapterEvents(Context mContext, List<Event> mEvents, boolean mLimit) {
        this.mContext = mContext;
        this.mEvents = mEvents;
        this.mLimit = mLimit;
    }

    @NonNull
    @Override
    public MyViewHolderEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycleview_row_layout,parent,false);

        return new MyViewHolderEvents(view, p -> {

            Intent intent = new Intent(mContext, EventDetailActivity.class);
            intent.putExtra("EventId", mEvents.get(p).getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public void onBindViewHolder(MyViewHolderEvents holder, final int position) {

        holder.event_title.setText(mEvents.get(position).getName());
        holder.event_d.setText(mEvents.get(position).getDescription());

        String[] dateEvent = mEvents.get(position).getDate().toString().split("\\s+");

        holder.mDayText.setText(dateEvent[2]);
        holder.mMonthText.setText(dateEvent[1]);
        holder.mYearText.setText(dateEvent[5]);
    }

    @Override
    public int getItemCount() {
        if (mLimit)
                if (mEvents.size() > 2)
                    return 2;
        return mEvents.size();
    }

    public static class MyViewHolderEvents extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView event_title;
        public TextView event_d;
        public Button event_button;
        public TextView mDayText;
        public TextView mMonthText;
        public TextView mYearText;

        CardView cardView;
        MyClickListener listener;

        public MyViewHolderEvents(View itemView, MyClickListener listener) {
            super(itemView);
            event_title = (TextView) itemView.findViewById(R.id.event_title);
            event_d = (TextView) itemView.findViewById(R.id.event_d);
            cardView = (CardView) itemView.findViewById(R.id.event_card);
            event_button = (Button) itemView.findViewById(R.id.idbutton);
            mDayText = (TextView) itemView.findViewById(R.id.txtGiorno);
            mMonthText = (TextView) itemView.findViewById(R.id.txtMese);
            mYearText = (TextView) itemView.findViewById(R.id.txtAnno);


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
