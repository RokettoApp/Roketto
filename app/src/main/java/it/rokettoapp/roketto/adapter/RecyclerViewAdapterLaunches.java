package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.ui.EventDetailActivity;
import it.rokettoapp.roketto.ui.LaunchDetailActivity;

public class RecyclerViewAdapterLaunches extends RecyclerView.Adapter<RecyclerViewAdapterLaunches.MyViewHolderLaunches>{
    private List<Launch> mLaunches;
    private Context mContext;
    private boolean mLimit;

    public RecyclerViewAdapterLaunches(Context mContext, List<Launch> mLaunches, boolean mLimit) {
        this.mContext = mContext;
        this.mLaunches = mLaunches;
        this.mLimit = mLimit;
    }

    @Override
    public void onBindViewHolder(MyViewHolderLaunches holder, final int position) {

        holder.launch_title.setText(mLaunches.get(position).getName());
        holder.launch_d.setText(mLaunches.get(position).getMission().getDescription());

        holder.mDate.setVisibility(View.GONE);

        String[] dateLaunch = mLaunches.get(position).getNet().toString().split("\\s+");

        holder.mDateTime.setVisibility(View.VISIBLE);
        holder.mDateTime.setText(dateLaunch[2] + "/"+ dateLaunch[1]+ "/"+ dateLaunch[5]);

        holder.mChip.setVisibility(View.VISIBLE);
        holder.mChip.setText(mLaunches.get(position).getMission().getType());

        holder.mStatusLaunch.setVisibility(View.VISIBLE);
        holder.mStatusLaunch.setText(mLaunches.get(position).getLaunchStatus().getName());

        holder.mLaunchImage.setVisibility(View.VISIBLE);
        holder.mImage.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(mLaunches.get(position).getImage()).into(holder.mLaunchImage);

        holder.mStatusCircle.setVisibility(View.VISIBLE);

        holder.mHours.setVisibility(View.VISIBLE);
        holder.mHours.setText(mLaunches.get(position).getNet().getHours() + ":" + mLaunches.get(position).getNet().getMinutes());

        Log.d("Rv", mLaunches.get(position).getName() + " RV");
       /*
        holder.launch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (mLimit)
            if (mLaunches.size() > 2)
                return 2;
        return mLaunches.size();
    }

    @Override
    public MyViewHolderLaunches onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycleview_row_layout,parent,false);

        MyViewHolderLaunches mHolder = new MyViewHolderLaunches(view, new MyClickListener() {
            @Override
            public void onClick(int p) {
                Intent intent = new Intent(mContext, LaunchDetailActivity.class);
                intent.putExtra("Launch", mLaunches.get(p));
                mContext.startActivity(intent);
            }
        });

        return mHolder;
    }

    public static class MyViewHolderLaunches extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView launch_title;
        public TextView launch_d;
        public Button launch_button;
        public TextView mStatusLaunch;
        public TextView mDateTime;
        public Chip mChip;
        public ViewGroup mDate;
        public ImageView mLaunchImage;
        public ImageView mStatusCircle;
        public TextView mHours;
        public CardView mImage;


        CardView cardView;
        MyClickListener listener;

        public MyViewHolderLaunches(View itemView, MyClickListener listener) {
            super(itemView);
            launch_title = (TextView) itemView.findViewById(R.id.event_title);
            launch_d = (TextView) itemView.findViewById(R.id.event_d);
            cardView = (CardView) itemView.findViewById(R.id.event_card);
            launch_button = (Button) itemView.findViewById(R.id.idbutton);
            mChip = (Chip) itemView.findViewById(R.id.chipCategory);
            mStatusLaunch = (TextView) itemView.findViewById(R.id.statusText);
            mDate = (ViewGroup) itemView.findViewById(R.id.date);
            mLaunchImage = (ImageView) itemView.findViewById(R.id.image);
            mDateTime = (TextView) itemView.findViewById(R.id.datetime);
            mStatusCircle = (ImageView) itemView.findViewById(R.id.statusCircle);
            mHours = (TextView) itemView.findViewById(R.id.hours);
            mImage = (CardView) itemView.findViewById(R.id.cardImg);


            this.listener = listener;

            launch_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { listener.onClick(this.getLayoutPosition()); }
    }

    public interface MyClickListener{
        void onClick(int p);
    }

}
