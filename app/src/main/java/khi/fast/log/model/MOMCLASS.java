package khi.fast.log.model;

public class MOMCLASS {
    private String NAME;
    private String PICTURE;
    private String wicketsAndRuns;

    public MOMCLASS(){

    }

    public MOMCLASS(String name, String wicketsARuns, String picture){
        NAME=name;
        wicketsAndRuns=wicketsARuns;
        PICTURE=picture;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPICTURE() {
        return PICTURE;
    }

    public void setPICTURE(String PICTURE) {
        this.PICTURE = PICTURE;
    }

    public String getWicketsAndRuns() {
        return wicketsAndRuns;
    }

    public void setWicketsAndRuns(String wicketsAndRuns) {
        this.wicketsAndRuns = wicketsAndRuns;
    }
}