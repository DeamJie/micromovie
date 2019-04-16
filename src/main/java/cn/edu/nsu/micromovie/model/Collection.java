package cn.edu.nsu.micromovie.model;

public class Collection {
    private Integer collectionid;

    private Integer uid;

    private Integer movieid;

    public Integer getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(Integer collectionid) {
        this.collectionid = collectionid;
    }

    public Integer getUserid() {
        return uid;
    }

    public void setUserid(Integer userid) {
        this.uid = userid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }
}