package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterNews;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.ui.viewmodel.ArticleViewModel;

public class FragmentNews extends Fragment {

    private ArticleViewModel mArticleViewModel;
    private List<Article> mArticle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mArticleViewModel = new ViewModelProvider(requireActivity()).get(ArticleViewModel.class);
        mArticle = new ArrayList<>();

        mArticle.add(new Article(0,"Prova","url", "url","Sky tg","dcdcdd",new Date(2021,11,10),null,false,null,null));
        mArticle.add(new Article(1,"Prova","url", "url","Sky tg","dcdcdd",new Date(2021,11,10),null,false,null,null));
        mArticle.add(new Article(2,"Prova","url", "url","Sky tg","dcdcdd",new Date(2021,11,10),null,false,null,null));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_news,container, false);

        RecyclerView mRecyclerNews = (RecyclerView) rootView.findViewById(R.id.rvNews);
        RecyclerViewAdapterNews mRecyclerViewAdapterNews = new RecyclerViewAdapterNews(mArticle,getContext());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerNews.setLayoutManager(llm);
        mRecyclerNews.setAdapter( mRecyclerViewAdapterNews );


        TextView textView = rootView.findViewById(R.id.textView2);

        mArticleViewModel.getArticleLiveData().observe(getViewLifecycleOwner(), articleList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Article article : articleList) {
                stringBuilder.append(article.getTitle()).append("\n");
            }
            textView.append(stringBuilder.toString());
            Log.d("ArticleObserver", "test");
        });

        mArticleViewModel.getReportLiveData().observe(getViewLifecycleOwner(), reportList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Article report : reportList) {
                stringBuilder.append(report.getTitle()).append("\n");
            }
            textView.append(stringBuilder.toString());
            Log.d("ReportObserver", "test");
        });

        mArticleViewModel.getBlogPostLiveData().observe(getViewLifecycleOwner(), blogPostList -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (Article blogPost : blogPostList) {
                stringBuilder.append(blogPost.getTitle()).append("\n");
            }
            textView.append(stringBuilder.toString());
            Log.d("BlogPostObserver", "test");
        });

        mArticleViewModel.getArticles();
        mArticleViewModel.getReports();
        mArticleViewModel.getBlogPosts();

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(view -> {

            textView.setText("");
            mArticleViewModel.refreshArticles();
        });

        return rootView;
    }
}
