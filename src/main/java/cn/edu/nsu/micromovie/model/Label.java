package cn.edu.nsu.micromovie.model;

public class Label {
    private Integer labelid;

    private String name;

    private int survive;

    public int getSurvive() {
        return survive;
    }

    public void setSurvive(int survive) {
        this.survive = survive;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}