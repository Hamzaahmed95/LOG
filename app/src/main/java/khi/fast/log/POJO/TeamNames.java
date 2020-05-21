package khi.fast.log.POJO;

/**
 * Created by Hamza Ahmed on 03-Oct-17.
 */

public class TeamNames {

    private String name1;
    private String name2;

    public TeamNames(String name1,String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public TeamNames() {
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
