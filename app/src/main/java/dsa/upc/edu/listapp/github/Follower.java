package dsa.upc.edu.listapp.github;

public class Follower {
    public final String login;
    public final int contributions;
    public final String avatar_url;

    public Follower(String login, int contributions, String avatar_url) {
        this.login = login;
        this.contributions = contributions;
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return login+" ("+contributions+")";
    }
}
