package company.by.myappp.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import company.by.myappp.model.UserModel;
import company.by.myappp.view.MainViewInterface;

/**
 * Created by Egor on 11.01.2018.
 */

public interface UserPresenterInterface extends MvpPresenter<MainViewInterface> {
    void show();
    UserModel getModel();

}
