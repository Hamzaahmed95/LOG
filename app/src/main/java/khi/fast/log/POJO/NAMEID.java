package khi.fast.log.POJO;

/**
 * Created by Hamza Ahmed on 11-Oct-17.
 */

public class NAMEID {
    private String name;
    private String id;


    public NAMEID(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public NAMEID() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
