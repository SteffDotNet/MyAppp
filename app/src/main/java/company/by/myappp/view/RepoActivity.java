package company.by.myappp.view;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import company.by.myappp.R;
import company.by.myappp.adapter.RepoAdapter;
import company.by.myappp.presenter.RepoPresenter;
import company.by.myappp.presenter.RepoPresenterInterface;

public class RepoActivity extends MvpActivity<RepoViewInterface, RepoPresenterInterface> implements RepoViewInterface {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RepoAdapter adapter;
    private long idUser;

    private BroadcastReceiver broadcastReceiver;
    public static final String BROADCAST_ACTION = "RepoService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        recyclerView = (RecyclerView) findViewById(R.id.listRepos);
        layoutManager = new LinearLayoutManager(RepoActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(getPresenter().getModel().getRepoAdapter());
        show(getIntent().getStringExtra("name"));
    }

    @NonNull
    @Override
    public RepoPresenterInterface createPresenter() {
        return new RepoPresenter(this);
    }

    @Override
    public void show(String name) {
        getPresenter().show(name);
    }
}
