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
import it.rokettoapp.roketto.model.Astronaut;

public class RecyclerViewAdapterAstro extends RecyclerView.Adapter<RecyclerViewAdapterAstro.MyViewHolderAstro> {

    private List<Astronaut> mAstro;
    private Context mContext;

    public RecyclerViewAdapterAstro(Context mContext, List<Astronaut> mAstro) {
        this.mContext = mContext;
        this.mAstro = mAstro;
    }

    @Override
    public MyViewHolderAstro onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycler_astronauts_item,parent,false);
        return new MyViewHolderAstro(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderAstro holder, final int position) {

        holder.astro_name.setText(mAstro.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mAstro.size();
    }

    public static class MyViewHolderAstro extends RecyclerView.ViewHolder {

        public TextView astro_name;
        CardView cardView;

        public MyViewHolderAstro(View itemView) {
            super(itemView);
            astro_name = (TextView) itemView.findViewById(R.id.astronautsName);
            cardView = (CardView) itemView.findViewById(R.id.astro_card);
        }
    }


}
