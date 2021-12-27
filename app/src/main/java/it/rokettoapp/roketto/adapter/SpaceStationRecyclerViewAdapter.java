package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.SpaceStation;

public class SpaceStationRecyclerViewAdapter
        extends RecyclerView.Adapter<SpaceStationRecyclerViewAdapter.SpaceStationViewHolder> {

    private final List<SpaceStation> mSpaceStationList;
    private final Context mContext;

    public SpaceStationRecyclerViewAdapter(Context context, List<SpaceStation> spaceStationList) {

        this.mSpaceStationList = spaceStationList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public SpaceStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.recycleview_row_layout, parent, false);

        return new SpaceStationViewHolder(view, p -> {

//            Intent intent = new Intent(mContext, SpaceStationActivity.class);
//            intent.putExtra("SpaceStation", mSpaceStationList.get(p));
//            mContext.startActivity(intent);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceStationViewHolder holder, int position) {

        holder.name.setText(mSpaceStationList.get(position).getName());
        holder.description.setText(mSpaceStationList.get(position).getDescription());
        holder.status.setText(mSpaceStationList.get(position)
                .getSpaceStationStatus().getName());
    }

    @Override
    public int getItemCount() {

        return mSpaceStationList.size();
    }

    public static class SpaceStationViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView name;
        public TextView description;
        public TextView status;
        public Button event_button;

//        CardView cardView;
        SpaceStationRecyclerViewAdapter.ClickListener listener;

        public SpaceStationViewHolder(View itemView,
                                      SpaceStationRecyclerViewAdapter.ClickListener listener) {

            super(itemView);
            this.listener = listener;

            name = (TextView) itemView.findViewById(R.id.event_title);
            description = (TextView) itemView.findViewById(R.id.event_d);
//            cardView = (CardView) itemView.findViewById(R.id.event_card);
            event_button = (Button) itemView.findViewById(R.id.idbutton);
            event_button.setOnClickListener(this);

            itemView.findViewById(R.id.statusCircle).setVisibility(View.VISIBLE);
            status = (TextView) itemView.findViewById(R.id.statusText);
            status.setVisibility(View.VISIBLE);

            itemView.findViewById(R.id.date).setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(this.getLayoutPosition());
        }
    }

    public interface ClickListener{

        void onClick(int p);
    }
}
