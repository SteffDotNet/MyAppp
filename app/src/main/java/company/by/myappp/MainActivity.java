package company.by.myappp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import company.by.myappp.adapter.UserAdapter;
import company.by.myappp.model.User;
import company.by.myappp.retrofit.GitHubAPI;
import company.by.myappp.service.MyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdapter adapter;

    private BroadcastReceiver broadcastReceiver;
    public static final String BROADCAST_ACTION = "MyService";
    public static final int PENDING_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listUser);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra("status",0);

                switch (status){
                    case MyService.STATUS_ERROR:
                        break;

                    case MyService.STATUS_OK:
                        ArrayList<User> users = (ArrayList<User>)intent.getSerializableExtra("users");
                        adapter = new UserAdapter(MainActivity.this, users);
                        recyclerView.setAdapter(adapter);
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        startService(new Intent(MainActivity.this, MyService.class));

    }
}
