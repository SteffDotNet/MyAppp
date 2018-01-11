package company.by.myappp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import company.by.myappp.adapter.UserAdapter;
import company.by.myappp.db.DBService;
import company.by.myappp.retrofit.GitHubAPI;
import company.by.myappp.retrofit.GitHubService;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Egor on 11.01.2018.
 */

public class UserModel {

    private Context context;
    private List<User> users;
    private CompositeDisposable compositeDisposable;
    private GitHubAPI api;
    private DBService dbService;

    private UserAdapter userAdapter;

    public UserModel(Context context) {
        this.context = context;
        dbService = new DBService(context);
        compositeDisposable = new CompositeDisposable();
        api = GitHubService.getRetrofit().create(GitHubAPI.class);
        users = new ArrayList<>();
        userAdapter = new UserAdapter(context, users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUserAdapter(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public GitHubAPI getApi() {
        return api;
    }

    public UserAdapter getUserAdapter() {
        return userAdapter;
    }

    public Context getContext() {
        return context;
    }

    public DBService getDbService() {
        return dbService;
    }
}
