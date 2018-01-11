package company.by.myappp.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import company.by.myappp.model.RepoModel;
import company.by.myappp.model.Repository;
import company.by.myappp.view.RepoViewInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Egor on 11.01.2018.
 */

public class RepoPresenter extends MvpBasePresenter<RepoViewInterface> implements RepoPresenterInterface {

    private RepoModel model;

    public RepoPresenter(Context context) {
        model = new RepoModel(context);
    }

    @Override
    public void show(String name) {
        model.getCompositeDisposable().add(model.getApi().getRepositories(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    @Override
    public RepoModel getModel() {
        return model;
    }

    private void handleResponse(List<Repository> list) {
        model.setRepos(list);
        model.getCompositeDisposable().clear();
        model.getRepoAdapter().setRepos(model.getRepos());
    }

    private void handleError(Throwable t) {

    }


}
