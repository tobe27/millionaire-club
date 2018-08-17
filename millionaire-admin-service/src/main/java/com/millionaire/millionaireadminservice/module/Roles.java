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
}