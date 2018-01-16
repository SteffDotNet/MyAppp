package company.by.myappp.presenter;

import android.content.Context;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.Random;
import company.by.myappp.R;
import company.by.myappp.model.SearchResult;
import company.by.myappp.model.UserModel;
import company.by.myappp.service.ConnectivityService;
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
    public void showUserList() {
        Random random = new Random();
        int since = random.nextInt(10000)+1;

        if(ConnectivityService.isConnectInternet(model.getContext())){
            model.getCompositeDisposable().add(model.getApi().getUsers()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }else{
            model.setUsers(model.getDbService().getUsers());
            getView().showListUsers(model.getUsers());
            if(model.getUsers().size() > 0) getView().showMsg(model.getContext().getResources().getString(R.string.no_internet_сonn));
            else getView().showMsg(model.getContext().getResources().getString(R.string.no_internet_сonn2));
        }
    }

    private void handleResponse(SearchResult result) {
        //Log.d("TAG", "getRandom : " + list.size());
        model.setUsers(result.getUsers());
        model.getCompositeDisposable().clear();
        model.getDbService().saveUsers(model.getUsers());

        getView().showListUsers(result.getUsers());
    }

    private void handleError(Throwable t) {
    }



}
