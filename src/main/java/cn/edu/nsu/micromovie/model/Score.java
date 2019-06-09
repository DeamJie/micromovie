package cn.edu.nsu.micromovie.model;

public class Score {
    private Integer id;

    private Integer userid;

    private Integer moveid;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMoveid() {
        return moveid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setMoveid(Integer moveid) {
        this.moveid = moveid;
    }
}