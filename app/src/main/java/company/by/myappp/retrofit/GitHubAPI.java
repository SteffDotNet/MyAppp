package company.by.myappp.retrofit;

import java.util.List;

import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Egor on 08.01.2018.
 */

public interface GitHubAPI {

    //https://api.github.com/users?since=1&per_page=10
    @GET("/users")
    Observable<List<User>> getRandomUsers(@Query("since") int id, @Query("per_page") int count);

    @GET("/users/{name}/repos")
    Observable<List<Repository>> getRepositories(@Path("name") String name);
}
