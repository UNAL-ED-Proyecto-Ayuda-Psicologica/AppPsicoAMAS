package PsicObj;

public class Panic {
    private User user;
    private String situation;
    private boolean isSolved;

    public Panic(User user, String situation) {
        this.user = user;
        this.situation = situation;
        this.isSolved=false;
    }

    public User getUser() {
        return user;
    }

    public String getSituation() {
        return situation;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void solve(){
        this.isSolved=true;
    }
}
