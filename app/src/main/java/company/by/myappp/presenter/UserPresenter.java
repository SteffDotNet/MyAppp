package company.by.myappp.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import company.by.myappp.model.UserModel;
import company.by.myappp.model.User;
import company.by.myappp.view.MainViewInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Egor on 11.01.2018.
 */

public class UserPresenter extends MvpBasePresenter<MainViewInterface> implements UserPresenterInterface {

    private UserModel model;

    public UserPresenter(Context context) {
        model = new UserModel(context);
    }

    @Override
    public void show() {
        model.getCompositeDisposable().add(model.getApi().getRandomUsers(10, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    @Override
    public UserModel getModel() {
        return model;
    }

    private void handleResponse(List<User> list) {
        model.setUsers(list);
        model.getCompositeDisposable().clear();
        model.getUserAdapter().setUsers(model.getUsers());
        model.getDbService().saveUsers(model.getUsers());
    }

    private void handleError(Throwable t) {
        model.setUsers(model.getDbService().getUsers());
        model.getUserAdapter().setUsers(model.getUsers());
    }



}
