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

    public int getFollowing() {
        return following;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public  String login = "";
    public final String avatar_url;
    public final int followers;
    public final int following;
    public User(String login, int following, String avatar_url, int followers) {
        this.login = login;
        this.followers = followers;
        this.following = following;
        this.avatar_url = avatar_url;
    }
    @Override
    public String toString() {
        return login+" ("+following+")";
    }
}
