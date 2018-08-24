package com.millionaire.millionaireadminservice.module;

public class Roles {
    private Long id;

    private String roleName;

    private Long gmtCreate;

    private Long gmtUpdate;

    private String founder;

    private String modifier;

    public Roles(Long id, String roleName, Long gmtCreate, Long gmtUpdate, String founder, String modifier) {
        this.id = id;
        this.roleName = roleName;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.founder = founder;
        this.modifier = modifier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public String getFounder() {
        return founder;
    }

    public String getModifier() {
        return modifier;
    }

    public Roles() {
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                ", founder='" + founder + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}