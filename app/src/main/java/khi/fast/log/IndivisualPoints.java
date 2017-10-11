package khi.fast.log;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class IndivisualPoints {

    private String name;
    private int points;
    private int count;

    public IndivisualPoints(String name, int points,int count) {
        this.name = name;
        this.points = points;
        this.count=count;
    }

    public IndivisualPoints() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
