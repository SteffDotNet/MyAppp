package company.by.myappp.presenter;

import android.graphics.Bitmap;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import java.util.List;

import company.by.myappp.model.RepoModel;
import company.by.myappp.model.User;
import company.by.myappp.view.RepoViewInterface;

/**
 * Created by Egor on 11.01.2018.
 */

public interface RepoPresenterInterface extends MvpPresenter<RepoViewInterface> {
    void showRepoList(long id);
    void showUserInfo(long id);
    Bitmap getImage(String path);
}
