package cn.edu.nsu.micromovie.model;

public class Admin {
    private Integer adminid;

    private String name;

    private String passworld;

    private String oldPassworld;

    private String salt;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOldPassworld() {
        return oldPassworld;
    }

    public void setOldPassworld(String oldPassworld) {
        this.oldPassworld = oldPassworld;
    }
}