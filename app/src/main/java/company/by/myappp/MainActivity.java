package company.by.myappp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import company.by.myappp.adapter.UserAdapter;
import company.by.myappp.model.User;
import company.by.myappp.service.UserService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdapter adapter;

    private BroadcastReceiver broadcastReceiver;
    public static final String BROADCAST_ACTION = "UserService";
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
                    case UserService.STATUS_ERROR:
                        break;

                    case UserService.STATUS_OK:
                        ArrayList<User> users = (ArrayList<User>)intent.getSerializableExtra("users");
                        String login = users.get(0).getLogin();
                        Toast.makeText(MainActivity.this,"size = " + users.size() + "  " +  login,Toast.LENGTH_SHORT).show();
                        adapter = new UserAdapter(MainActivity.this, users);
                        recyclerView.setAdapter(adapter);
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        startService(new Intent(MainActivity.this, UserService.class));

    }
}
