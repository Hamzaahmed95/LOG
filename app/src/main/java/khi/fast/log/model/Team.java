package khi.fast.log.model;

import java.util.HashMap;
import java.util.List;

public class Team {
    private String logo;
    private String name;
    private List<Player> players;

    public Team(String logo,String name,List<Player> players){
        this.logo=logo;
        this.name=name;
        this.players = players;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
