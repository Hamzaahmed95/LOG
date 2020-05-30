package khi.fast.log.model;

/**
 * Created by Hamza Ahmed on 08-Oct-17.
 */

public class UsersFantacyTeam {

    private String userId;
    private String Goalkeeper;
    private String Defender1;
    private String Defender2;
    private String Striker1;
    private String Striker2;

    public UsersFantacyTeam() {

    }

    public UsersFantacyTeam(String userId, String goalkeeper, String defender1, String defender2, String striker1, String striker2) {
        this.userId = userId;
        Goalkeeper = goalkeeper;
        Defender1 = defender1;
        Defender2 = defender2;
        Striker1 = striker1;
        Striker2 = striker2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoalkeeper() {
        return Goalkeeper;
    }

    public void setGoalkeeper(String goalkeeper) {
        Goalkeeper = goalkeeper;
    }

    public String getDefender1() {
        return Defender1;
    }

    public void setDefender1(String defender1) {
        Defender1 = defender1;
    }

    public String getDefender2() {
        return Defender2;
    }

    public void setDefender2(String defender2) {
        Defender2 = defender2;
    }

    public String getStriker1() {
        return Striker1;
    }

    public void setStriker1(String striker1) {
        Striker1 = striker1;
    }

    public String getStriker2() {
        return Striker2;
    }

    public void setStriker2(String striker2) {
        Striker2 = striker2;
    }
}
