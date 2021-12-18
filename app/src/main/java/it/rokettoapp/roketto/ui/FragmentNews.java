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

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.ui.viewmodel.ArticleViewModel;

public class FragmentNews extends Fragment {

    private ArticleViewModel mArticleViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mArticleViewModel = new ViewModelProvider(requireActivity()).get(ArticleViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_news,container, false);

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
