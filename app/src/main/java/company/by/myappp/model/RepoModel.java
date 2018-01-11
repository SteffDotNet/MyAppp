package company.by.myappp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import company.by.myappp.adapter.RepoAdapter;
import company.by.myappp.retrofit.GitHubAPI;
import company.by.myappp.retrofit.GitHubService;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Egor on 11.01.2018.
 */

public class RepoModel {

    private Context context;
    private List<Repository> repos;
    private RepoAdapter repoAdapter;
    private CompositeDisposable compositeDisposable;
    private GitHubAPI api;


    public RepoModel(Context context) {
        this.context = context;
        compositeDisposable = new CompositeDisposable();
        api = GitHubService.getRetrofit().create(GitHubAPI.class);
        repos = new ArrayList<>();
        repoAdapter = new RepoAdapter(context, repos);

    }

    public List<Repository> getRepos() {
        return repos;
    }

    public void setRepos(List<Repository> repos) {
        this.repos = repos;
    }

    public Context getContext() {
        return context;
    }

    public RepoAdapter getRepoAdapter() {
        return repoAdapter;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public GitHubAPI getApi() {
        return api;
    }
}
