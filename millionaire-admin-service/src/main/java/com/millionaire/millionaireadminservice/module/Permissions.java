package com.millionaire.millionaireadminservice.module;

public class Permissions {
    private Long id;

    private String permission;

    private String visible;

    private Long gmtCreate;

    private Long gmtUpdate;

    public Permissions(Long id, String permission, String visible, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.permission = permission;
        this.visible = visible;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public Permissions() {
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", visible='" + visible + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }
}