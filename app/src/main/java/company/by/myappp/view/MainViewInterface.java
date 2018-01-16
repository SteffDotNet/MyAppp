package company.by.myappp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import company.by.myappp.model.User;

/**
 * Created by Egor on 11.01.2018.
 */

public interface MainViewInterface extends MvpView {
    void showListUsers(List<User> users);
    void showMsg(String msg);
}
