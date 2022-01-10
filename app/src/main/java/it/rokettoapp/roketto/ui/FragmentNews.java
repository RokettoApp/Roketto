package it.rokettoapp.roketto.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
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

        mArticleViewModel.getArticleLiveData().observe(getViewLifecycleOwner(), response -> {

            mArticle.clear();
            if (!response.isError()) {
                mArticle.addAll(response.getResults());
                if(mArticle.size()<100)
                    mArticle.add(null);
                mRecyclerViewAdapterNews.notifyDataSetChanged();
                mArticleViewModel.setLoading(false);
                mSwipe.setRefreshing(false);
                Log.d("FragmentNews", "test");
            } else {
                showError(response.getMessage());
            }
        });
        mArticleViewModel.getArticles(isConnected());

        mSwipe.setOnRefreshListener(() -> mArticleViewModel.refreshArticles(isConnected()));

        mRecyclerNews.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (mArticle.size() < 100) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(1) && !mArticleViewModel.isLoading()) {
                        if (mArticleViewModel.getArticleLiveData().getValue() != null && isConnected()) {
                            Log.d("BlogPostObserver", "test3");
                            mArticleViewModel.setLoading(true);
                            mArticleViewModel.getNewArticles();
                        } else {
                            if (!isConnected()) {
                                showError(getString(R.string.connection_error));
                            }
                        }
                    }
                }
            }
        });

        return rootView;
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showError(String errorMessage) {

        Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}
