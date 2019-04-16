package cn.edu.nsu.micromovie.util;

public class EvaluationUtil {
    private String name;
    private String evaluation;

    public String getName() {
        return name;
    }

    public EvaluationUtil(String name, String evaluation) {
        this.name = name;
        this.evaluation = evaluation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
