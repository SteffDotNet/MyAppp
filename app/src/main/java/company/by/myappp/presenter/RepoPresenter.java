package company.by.myappp.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import company.by.myappp.R;
import company.by.myappp.model.RepoModel;
import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import company.by.myappp.service.ConnectivityService;
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
    public void showRepoList(long id) {
        User user = model.getUser();

        if(ConnectivityService.isConnectInternet(model.getContext())){
            model.getCompositeDisposable().add(model.getApi().getRepositories(user.getLogin(), "created")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }else{
            List<Repository> repos = model.getDbService().getRepos(model.getUser().getId());
            model.setRepos(repos);

            if(repos.size() > 0) {
                getView().showRepoList(model.getRepos());
            }else getView().showMsg(model.getContext().getResources().getString(R.string.no_load_repos));
        }

    }

    @Override
    public void showUserInfo(long id) {
        User user = model.getDbService().getUserById(id);
        model.setUser(user);
        getView().showUserInfo(user);
    }

    @Override
    public Bitmap getImage(String path) {
        return model.getDbService().getPicture(path);
    }

    private void handleResponse(List<Repository> list) {
        model.setRepos(list);
        model.getCompositeDisposable().clear();
        getView().showRepoList(list);
        model.getDbService().saveRepos(model.getUser().getId(), model.getRepos());
    }

    private void handleError(Throwable t) {

    }


}
