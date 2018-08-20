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

    public void setId(Long id) {
        this.id = id;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "RolesPermissions{" +
                "id=" + id +
                ", permissionId=" + permissionId +
                ", roleId=" + roleId +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public RolesPermissions() {
    }
}