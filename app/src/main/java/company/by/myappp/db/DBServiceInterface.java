package company.by.myappp.db;

import android.graphics.Bitmap;

import java.util.List;

import company.by.myappp.model.Repository;
import company.by.myappp.model.User;

/**
 * Created by Egor on 11.01.2018.
 */

public interface DBServiceInterface {
    void saveUsers(List<User> users);
    void saveRepos(long id_user, List<Repository> repos);
    void savePicture(String path);
    User getUserById(long id);
    List<User> getUsers();
    List<Repository> getRepos(long id_user);
    Bitmap getPicture(String path);

}
