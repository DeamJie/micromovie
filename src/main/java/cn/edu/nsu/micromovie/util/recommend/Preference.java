package cn.edu.nsu.micromovie.util.recommend;

public class Preference {
    private double scoreLabelScale;
    private double connectionLabelScale;
    private double clickLabelScale;
    private int scoreLabelId;
    private int connectionLabelId;
    private int clickLabelId;


    public Preference(){
        this.scoreLabelScale=0.7D;
        this.connectionLabelScale=0.2D;
        this.clickLabelScale=0.1D;
    }

    public void clickLabelScaleIncrease(){
        if (this.clickLabelScale<0.8D){
            this.clickLabelScale=this.clickLabelScale+0.1D;
            if (this.scoreLabelScale>0.5D){
                this.scoreLabelScale = this.scoreLabelScale-0.1D;
            }else {
                this.scoreLabelScale = this.scoreLabelScale-0.05D;
                this.connectionLabelScale = this.connectionLabelScale-0.05D;
            }
        }
    }

    public double getScoreLabelScale() {
        return scoreLabelScale;
    }

    public void setScoreLabelScale(double scoreLabelScale) {
        this.scoreLabelScale = scoreLabelScale;
    }

    public double getConnectionLabelScale() {
        return connectionLabelScale;
    }

    public void setConnectionLabelScale(double connectionLabelScale) {
        this.connectionLabelScale = connectionLabelScale;
    }

    public double getClickLabelScale() {
        return clickLabelScale;
    }

    public void setClickLabelScale(double clickLabelScale) {
        this.clickLabelScale = clickLabelScale;
    }

    public int getScoreLabelId() {
        return scoreLabelId;
    }

    public void setScoreLabelId(int scoreLabelId) {
        this.scoreLabelId = scoreLabelId;
    }

    public int getConnectionLabelId() {
        return connectionLabelId;
    }

    public void setConnectionLabelId(int connectionLabelId) {
        this.connectionLabelId = connectionLabelId;
    }

    public int getClickLabelId() {
        return clickLabelId;
    }

    public void setClickLabelId(int clickLabelId) {
        this.clickLabelId = clickLabelId;
    }
}
