package dsa.upc.edu.listapp.github;

public class User {
    public int getFollowers() {
        return followers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public  String login = "";
    public final int public_repos;
    public final String avatar_url;
    public final int followers;
    public User(String login, int public_repos, String avatar_url, int followers) {
        this.login = login;
        this.followers = followers;
        this.public_repos = public_repos;
        this.avatar_url = avatar_url;
    }
    @Override
    public String toString() {
        return login+" ("+public_repos+")";
    }
}
