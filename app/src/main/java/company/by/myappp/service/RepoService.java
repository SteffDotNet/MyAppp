package company.by.myappp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import company.by.myappp.MainActivity;
import company.by.myappp.RepoActivity;
import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import company.by.myappp.retrofit.GitHubAPI;
import company.by.myappp.retrofit.GitHubService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Egor on 08.01.2018.
 */

public class RepoService extends Service {

    public static final int STATUS_OK = 1;
    public static final int STATUS_ERROR = 0;
    private int startId;

    private Intent broadcastIntent;
    private GitHubAPI api;
    private CompositeDisposable compositeDisposable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        broadcastIntent = new Intent(RepoActivity.BROADCAST_ACTION);
        compositeDisposable = new CompositeDisposable();
        api = GitHubService.getRetrofit().create(GitHubAPI.class);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        String name = intent.getStringExtra("name");

        compositeDisposable.add(api.getRepositories(name)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleResponse, this::handleError));

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void handleResponse(List<Repository> list){
        broadcastIntent.putExtra("status", STATUS_OK);
        broadcastIntent.putExtra("repos", (Serializable) list);
        sendBroadcast(broadcastIntent);
        stopSelf(this.startId);

    }

    private void handleError(Throwable t){
        Toast.makeText(this,t.toString(),Toast.LENGTH_SHORT).show();
        broadcastIntent.putExtra("status", STATUS_ERROR);
        sendBroadcast(broadcastIntent);
        stopSelf(this.startId);
    }


}
