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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

        if(mArticle == null) mArticle = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_news,container, false);

        SwipeRefreshLayout mSwipe = rootView.findViewById(R.id.swipeNews);

        RecyclerView mRecyclerNews = (RecyclerView) rootView.findViewById(R.id.rvNews);
        RecyclerViewAdapterNews mRecyclerViewAdapterNews = new RecyclerViewAdapterNews(mArticle,getContext());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerNews.setLayoutManager(llm);
        mRecyclerNews.setAdapter( mRecyclerViewAdapterNews );

        mArticleViewModel.getArticleLiveData().observe(getViewLifecycleOwner(), articleList -> {

            mArticle.clear();
            mArticle.addAll(articleList);
            if(mArticle.size()<100)
                mArticle.add(null);
            mRecyclerViewAdapterNews.notifyDataSetChanged();
            mArticleViewModel.setLoading(false);
            mSwipe.setRefreshing(false);
            Log.d("FragmentNews", "test");
        });
        mArticleViewModel.getArticles();

        /*TextView textView = rootView.findViewById(R.id.textView2);

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
        mArticleViewModel.getReports();
        mArticleViewModel.getBlogPosts();

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(view -> {

            textView.setText("");
            mArticleViewModel.refreshArticles();
        });*/

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mArticleViewModel.refreshArticles();
            }
        });

        mRecyclerNews.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (mArticle.size() < 100) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(1) && !mArticleViewModel.isLoading()) {
                        if (mArticleViewModel.getArticleLiveData().getValue() != null) {
                            Log.d("BlogPostObserver", "test3");
                            mArticleViewModel.setLoading(true);
                            mArticleViewModel.getNewArticles();
                        }
                    }
                }
            }
        });

        return rootView;
    }
}
