package PsicObj;

public class Panic {
    private NoPsico user;
    private String situation;
    private boolean isSolved;
    private Psico psico;

    public Panic(NoPsico user, String situation) {
        this.user = user;
        this.situation = situation;
        this.isSolved=false;
    }

    public Psico getPsico() {
        return psico;
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

    public void solve(Psico psico) {
        this.psico=psico;
        this.isSolved=true;
    }
}
