package company.by.myappp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import company.by.myappp.R;
import company.by.myappp.adapter.RepoAdapter;
import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import company.by.myappp.presenter.RepoPresenter;
import company.by.myappp.presenter.RepoPresenterInterface;

public class RepoActivity extends MvpActivity<RepoViewInterface, RepoPresenterInterface> implements RepoViewInterface {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RepoAdapter adapter;
    private TextView textLogin;
    private TextView textURL;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        long id_user = getIntent().getLongExtra("id_user", 0);

        textURL= (TextView) findViewById(R.id.userURL);
        textLogin = (TextView) findViewById(R.id.loginUser);
        image = (ImageView) findViewById(R.id.avatarUser);

        getPresenter().showUserInfo(id_user);

        recyclerView = (RecyclerView) findViewById(R.id.listRepos);
        layoutManager = new LinearLayoutManager(RepoActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        getPresenter().showRepoList(id_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id_user = getIntent().getLongExtra("id_user", 0);
        getPresenter().showRepoList(id_user);
        return super.onOptionsItemSelected(item);
    }


    @NonNull
    @Override
    public RepoPresenterInterface createPresenter() {
        return new RepoPresenter(this);
    }

    @Override
    public void showRepoList(List<Repository> repos) {
        if(adapter == null){
            adapter = new RepoAdapter(RepoActivity.this, repos);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setRepos(repos);
        }
    }

    @Override
    public void showUserInfo(User user) {
        textLogin.setText(user.getLogin());
        textURL.setText(user.getGithub_url());

        Picasso.with(this).load(user.getAvatar_url()).placeholder(R.mipmap.ic_user).into(image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                image.setImageBitmap(getPresenter().getImage(user.getAvatar_url()));
            }
        });
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
