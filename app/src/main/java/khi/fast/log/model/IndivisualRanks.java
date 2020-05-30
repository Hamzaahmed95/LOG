package khi.fast.log.model;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class IndivisualRanks {

    private String name;
    private int ranks;

    public IndivisualRanks(String name, int ranks) {
        this.name = name;
        this.ranks = ranks;
    }

    public IndivisualRanks() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }
}
