package khi.fast.log;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class IndivisualPoints {

    private String name;
    private int points;

    public IndivisualPoints(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public IndivisualPoints() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
