package khi.fast.log.model;

/**
 * Created by Hamza Ahmed on 11-Oct-17.
 */

public class FantasyScoring {

    private int count;
    private String goalScorer;
    private String Assist;
    private String CleanSheets;
    private String savePenaltyGolkeeper;

    public FantasyScoring(String goalScorer, String assist, String cleanSheets, String savePenaltyGolkeeper,int count) {
        this.goalScorer = goalScorer;
        Assist = assist;
        CleanSheets = cleanSheets;
        this.savePenaltyGolkeeper = savePenaltyGolkeeper;
        this.count=count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FantasyScoring() {
    }

    public String getGoalScorer() {
        return goalScorer;
    }

    public void setGoalScorer(String goalScorer) {
        this.goalScorer = goalScorer;
    }

    public String getAssist() {
        return Assist;
    }

    public void setAssist(String assist) {
        Assist = assist;
    }

    public String getCleanSheets() {
        return CleanSheets;
    }

    public void setCleanSheets(String cleanSheets) {
        CleanSheets = cleanSheets;
    }

    public String getSavePenaltyGolkeeper() {
        return savePenaltyGolkeeper;
    }

    public void setSavePenaltyGolkeeper(String savePenaltyGolkeeper) {
        this.savePenaltyGolkeeper = savePenaltyGolkeeper;
    }
}
