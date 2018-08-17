package com.millionaire.millionaireadminservice.module;

public class Permissions {
    private Long id;

    private String permission;

    private Long gmtCreate;

    private Long gmtUpdate;

    public Permissions(Long id, String permission, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.permission = permission;
        this.gmtCreate = gmtCreate;
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
}