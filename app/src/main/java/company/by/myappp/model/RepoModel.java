package company.by.myappp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import company.by.myappp.adapter.RepoAdapter;
import company.by.myappp.db.DBService;
import company.by.myappp.retrofit.GitHubAPI;
import company.by.myappp.retrofit.GitHubService;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Egor on 11.01.2018.
 */

public class RepoModel {

    private Context context;
    private List<Repository> repos;
    private CompositeDisposable compositeDisposable;
    private GitHubAPI api;
    private DBService dbService;

    private User user;


    public RepoModel(Context context) {
        this.context = context;
        compositeDisposable = new CompositeDisposable();
        api = GitHubService.getRetrofit().create(GitHubAPI.class);
        repos = new ArrayList<>();
        dbService = new DBService(context);

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

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public GitHubAPI getApi() {
        return api;
    }

    public DBService getDbService() {
        return dbService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
