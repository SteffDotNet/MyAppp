package company.by.myappp.retrofit;

import java.util.List;

import company.by.myappp.model.Repository;
import company.by.myappp.model.SearchResult;
import company.by.myappp.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Egor on 08.01.2018.
 */

public interface GitHubAPI {


    @GET("/search/users?q=bert repos:5..30+followers:>35")
    Observable<SearchResult> getUsers();

    @GET("/users")
    Observable<List<User>> getRandomUsers(@Query("since") int id, @Query("per_page") int count);

    @GET("/users/{name}/repos")
    Observable<List<Repository>> getRepositories(@Path("name") String name, @Query("sort") String sort);
}
