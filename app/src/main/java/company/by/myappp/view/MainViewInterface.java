package company.by.myappp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Egor on 11.01.2018.
 */

public interface MainViewInterface extends MvpView {
    void showUsers();
    void updateUsers();
    void selectUser(long id);
}
