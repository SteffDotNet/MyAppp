package company.by.myappp.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import company.by.myappp.model.RepoModel;
import company.by.myappp.view.RepoViewInterface;

/**
 * Created by Egor on 11.01.2018.
 */

public interface RepoPresenterInterface extends MvpPresenter<RepoViewInterface> {
    void show(String name);
    RepoModel getModel();
}
