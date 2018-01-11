package company.by.myappp.view;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import company.by.myappp.R;
import company.by.myappp.presenter.UserPresenter;
import company.by.myappp.presenter.UserPresenterInterface;

public class MainActivity extends MvpActivity<MainViewInterface,UserPresenterInterface> implements MainViewInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listUser);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(getPresenter().getModel().getUserAdapter());

        showUsers();



    }

    @NonNull
    @Override
    public UserPresenterInterface createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void showUsers() {
        getPresenter().show();
    }

    @Override
    public void updateUsers() {

    }

    @Override
    public void selectUser(long id) {

    }
}
