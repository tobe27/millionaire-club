package com.millionaire.millionaireadminservice.module;

public class BackstageUsers {
    private Long id;

    private String name;

    private String password;

    private String salt;

    private String phone;

    private Long roleId;

    private Long gmtUpdate;

    private Long gmtCreate;

    private String founder;

    private String modifier;

    public BackstageUsers(Long id, String name, String password, String salt, String phone, Long roleId, Long gmtUpdate, Long gmtCreate, String founder, String modifier) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.roleId = roleId;
        this.gmtUpdate = gmtUpdate;
        this.gmtCreate = gmtCreate;
        this.founder = founder;
        this.modifier = modifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "BackstageUsers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId=" + roleId +
                ", gmtUpdate=" + gmtUpdate +
                ", gmtCreate=" + gmtCreate +
                ", founder='" + founder + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }

    public BackstageUsers() {
    }
}