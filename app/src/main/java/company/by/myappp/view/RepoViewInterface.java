package company.by.myappp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import company.by.myappp.model.Repository;
import company.by.myappp.model.User;

/**
 * Created by Egor on 11.01.2018.
 */

public interface RepoViewInterface extends MvpView {
    void showRepoList(List<Repository> repos);
    void showUserInfo(User user);
    void showMsg(String msg);
}
