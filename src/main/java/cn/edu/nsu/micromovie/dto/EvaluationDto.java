package cn.edu.nsu.micromovie.dto;

import java.util.Date;

public class EvaluationDto {
    private Integer evaluationid;
    private String name;
    private String movieName;
    private String evaluation;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public Integer getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(Integer evaluationid) {
        this.evaluationid = evaluationid;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
