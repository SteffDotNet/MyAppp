package company.by.myappp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import company.by.myappp.adapter.RepoAdapter;
import company.by.myappp.adapter.UserAdapter;
import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import company.by.myappp.service.RepoService;
import company.by.myappp.service.UserService;

public class RepoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RepoAdapter adapter;

    private BroadcastReceiver broadcastReceiver;
    public static final String BROADCAST_ACTION = "RepoService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        recyclerView = (RecyclerView) findViewById(R.id.listRepos);
        layoutManager = new LinearLayoutManager(RepoActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra("status",0);

                switch (status){
                    case UserService.STATUS_ERROR:
                        break;

                    case UserService.STATUS_OK:
                        ArrayList<Repository> repos = (ArrayList<Repository>)intent.getSerializableExtra("repos");
                        Toast.makeText(RepoActivity.this,"size = " + repos.size(),Toast.LENGTH_SHORT).show();
                        adapter = new RepoAdapter(RepoActivity.this, repos);
                        recyclerView.setAdapter(adapter);
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        startService(new Intent(RepoActivity.this, RepoService.class).putExtra("name", getIntent().getStringExtra("name")));

    }

}
