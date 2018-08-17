package com.millionaire.millionaireadminservice.module;

public class RolesPermissions {
    private Long id;

    private Long permissionId;

    private Long roleId;

    private Long gmtCreate;

    private Long gmtUpdate;

    public RolesPermissions(Long id, Long permissionId, Long roleId, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.permissionId = permissionId;
        this.roleId = roleId;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}