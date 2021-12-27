package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jwang123.flagkit.FlagKit;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.ui.AstroDetailActivity;
import it.rokettoapp.roketto.ui.EventDetailActivity;
import it.rokettoapp.roketto.util.CSVCountries;

public class RecyclerViewAdapterAstro extends RecyclerView.Adapter<RecyclerViewAdapterAstro.MyViewHolderAstro> {

    private List<Astronaut> mAstro;
    private Context mContext;
    private Drawable mFlag;
    private boolean mLimit;

    public RecyclerViewAdapterAstro(Context mContext, List<Astronaut> mAstro, boolean mLimit) {
        this.mContext = mContext;
        this.mAstro = mAstro;
        this.mLimit = mLimit;
    }

    @Override
    public MyViewHolderAstro onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.recycler_astronauts_item,parent,false);

        MyViewHolderAstro mHolder = new MyViewHolderAstro(view, new MyClickListener() {
            @Override
            public void onClick(int p) {
                Intent intent = new Intent(mContext, AstroDetailActivity.class);
                intent.putExtra("Astronaut", mAstro.get(p).getId());
                mContext.startActivity(intent);
            }
        });

        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolderAstro holder, final int position) {

        holder.astro_name.setText(mAstro.get(position).getName());
        holder.astro_name.setSelected(true);
        Glide.with(mContext).load(mAstro.get(position).getProfileImage()).into(holder.mImageAstro);
        String mNationality = mAstro.get(position).getNationality();

        CSVCountries mCountries = CSVCountries.getInstanceCountry();
        if(mCountries.checkCountryCode(mNationality))
            holder.nationalFlag.setImageDrawable(FlagKit.drawableWithFlag(mContext, mCountries.getCodeFromName(mNationality).toLowerCase()));


    }

    @Override
    public int getItemCount() {
        if(mLimit)
            if(mAstro.size() > 5)
                return 5;
        return mAstro.size();
    }

    public static class MyViewHolderAstro extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView astro_name;
        private ImageView mImageAstro;
        private ImageView nationalFlag;
        CardView cardView;

        MyClickListener listener;

        public MyViewHolderAstro(View itemView, MyClickListener listener) {
            super(itemView);
            astro_name = (TextView) itemView.findViewById(R.id.astronautsName);
            cardView = (CardView) itemView.findViewById(R.id.astro_card);
            mImageAstro = (ImageView) itemView.findViewById(R.id.imgAstronauts);
            nationalFlag = (ImageView) itemView.findViewById(R.id.nationalFlag);

            this.listener = listener;

            cardView.setOnClickListener(this);
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
