package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.ui.AstroDetailActivity;

public class RecyclerViewAdapterNews extends RecyclerView.Adapter<RecyclerViewAdapterNews.MyViewHolderNews>{
        private List<Article> mArticle;
        private Context mContext;

        public RecyclerViewAdapterNews(List<Article> mArticle, Context mContext) {
                this.mArticle = mArticle;
                this.mContext = mContext;
        }

        @NonNull
        @Override
        public MyViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view ;
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                view = mInflater.inflate(R.layout.recycler_news_item,parent,false);
                RecyclerViewAdapterNews.MyViewHolderNews mHolder = new RecyclerViewAdapterNews.MyViewHolderNews(view, new RecyclerViewAdapterNews.MyClickListener() {
                        @Override
                        public void onClick(int p) {

                        }
                });
                return mHolder;
        }


        public void onBindViewHolder(MyViewHolderNews holder, int position) {
                holder.news_title.setText(mArticle.get(position).getTitle());
                holder.news_description.setText(mArticle.get(position).getSummary());
                holder.news_date.setText(mArticle.get(position).getPublishedAt().toString());
                holder.news_publisher.setText(mArticle.get(position).getSource());
        }

        @Override
        public int getItemCount() {
                return mArticle.size();
        }

        public static class MyViewHolderNews extends RecyclerView.ViewHolder implements View.OnClickListener {

                public TextView news_title;
                public TextView news_description;
                public TextView news_date;
                public TextView news_publisher;
                private ImageView news_image;
                private Button news_read;

                CardView cardView;

                RecyclerViewAdapterNews.MyClickListener listener;

                public MyViewHolderNews(View itemView, RecyclerViewAdapterNews.MyClickListener listener) {
                        super(itemView);
                        news_title = (TextView) itemView.findViewById(R.id.news_title);
                        news_date = (TextView) itemView.findViewById(R.id.datetime_news);
                        news_publisher = (TextView) itemView.findViewById(R.id.publisher);
                        news_description = (TextView) itemView.findViewById(R.id.news_d);
                        cardView = (CardView) itemView.findViewById(R.id.news_card);
                        news_read = (Button) itemView.findViewById(R.id.readButton);
                        news_image = (ImageView) itemView.findViewById(R.id.imageNews);


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
