package cn.edu.nsu.micromovie.model;

public class Evaluation {
    private Integer evaluationid;

    private Integer uid;

    private Integer movieid;

    private String evaluation;

    public Integer getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(Integer evaluationid) {
        this.evaluationid = evaluationid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}