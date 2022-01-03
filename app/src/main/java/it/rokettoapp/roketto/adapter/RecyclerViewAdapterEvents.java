package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.databinding.DateItemLayoutBinding;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.ui.EventDetailActivity;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Event> mEvents;
    private Context mContext;
    private boolean mLimit;

    private static final int EVENTS_VIEW_TYPE = 0;
    private static final int LOADING_VIEW_TYPE = 1;

    public RecyclerViewAdapterEvents(Context mContext, List<Event> mEvents, boolean mLimit) {
        this.mContext = mContext;
        this.mEvents = mEvents;
        this.mLimit = mLimit;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        if(viewType == EVENTS_VIEW_TYPE) {
            view = mInflater.inflate(R.layout.recycleview_row_layout, parent, false);
            RecyclerViewAdapterEvents.MyViewHolderEvents mHolder =
                    new RecyclerViewAdapterEvents.MyViewHolderEvents(view, p -> {
                        Intent intent = new Intent(mContext, EventDetailActivity.class);
                        intent.putExtra("EventId", mEvents.get(p).getId());
                        mContext.startActivity(intent);
                    });
            return mHolder;
        }else{
            view = mInflater.inflate(R.layout.news_loading_item, parent, false);
            return new RecyclerViewAdapterNews.LoadingNewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolderEvents) {
            ((MyViewHolderEvents) holder).bind(mEvents.get(position));
        } else if (holder instanceof LoadingNewsViewHolder) {
            ((LoadingNewsViewHolder) holder).activate();
        }

    }

    @Override
    public int getItemCount() {
        if (mLimit)
                if (mEvents.size() > 2)
                    return 2;
        return mEvents.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mEvents.get(position) == null)
            return LOADING_VIEW_TYPE;
        else
            return EVENTS_VIEW_TYPE;
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

        public void bind(Event event){
            event_title.setText(event.getName());
            event_d.setText(event.getDescription());

            String[] dateEvent = event.getDate().toString().split("\\s+");

            mDayText.setText(dateEvent[2]);
            mMonthText.setText(dateEvent[1]);
            mYearText.setText(dateEvent[5]);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(this.getLayoutPosition());
        }
    }

    public interface MyClickListener{
        void onClick(int p);
    }

    public static class LoadingNewsViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        LoadingNewsViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressbar_loading_news);
        }

        public void activate() {
            progressBar.setIndeterminate(true);
        }
    }


}
