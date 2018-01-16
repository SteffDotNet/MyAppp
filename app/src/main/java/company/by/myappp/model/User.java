package company.by.myappp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Egor on 08.01.2018.
 */

@Entity
public class User implements Serializable{

    @Id(assignable = true)
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("type")
    private String type;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("html_url")
    private String github_url;

    @Backlink
    ToMany<Repository> repos;


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGithub_url() {
        return github_url;
    }

    public void setGithub_url(String github_url) {
        this.github_url = github_url;
    }


    public ToMany<Repository> getRepos() {
        return repos;
    }

    public void setRepos(ToMany<Repository> repos) {
        this.repos = repos;
    }
}
