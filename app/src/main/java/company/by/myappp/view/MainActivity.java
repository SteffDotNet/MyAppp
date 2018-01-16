package company.by.myappp.view;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import java.util.List;
import company.by.myappp.R;
import company.by.myappp.adapter.UserAdapter;
import company.by.myappp.model.User;
import company.by.myappp.presenter.UserPresenter;
import company.by.myappp.presenter.UserPresenterInterface;

public class MainActivity extends MvpActivity<MainViewInterface,UserPresenterInterface> implements MainViewInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listUser);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        getPresenter().showUserList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().showUserList();
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public UserPresenterInterface createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void showListUsers(List<User> users) {
        if(adapter == null){
            adapter = new UserAdapter(MainActivity.this, users);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setUsers(users);
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast toast = new Toast(this);

        View view = getLayoutInflater().inflate(R.layout.message, null, false);
        TextView text = view.findViewById(R.id.textMsg);
        text.setText(msg);

        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
