package company.by.myappp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Egor on 15.01.2018.
 */

public class SearchResult {
    @SerializedName("total_count")
    private int count_users;

    @SerializedName("items")
    private List<User> users;

    public SearchResult() {
    }

    public int getCount_users() {
        return count_users;
    }

    public void setCount_users(int count_users) {
        this.count_users = count_users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
