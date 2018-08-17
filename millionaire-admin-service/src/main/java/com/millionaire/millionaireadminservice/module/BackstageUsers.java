package com.millionaire.millionaireadminservice.module;

public class BackstageUsers {
    private Long id;

    private String name;

    private String password;

    private String salt;

    private String phone;

    private String role;

    private Long gmtUpdate;

    private Long gmtCreate;

    private String founder;

    private String modifier;

    public BackstageUsers(Long id, String name, String password, String salt, String phone, String role, Long gmtUpdate, Long gmtCreate, String founder, String modifier) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.role = role;
        this.gmtUpdate = gmtUpdate;
        this.gmtCreate = gmtCreate;
        this.founder = founder;
        this.modifier = modifier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public String getFounder() {
        return founder;
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public String toString() {
        return "BackstageUsers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", gmtUpdate=" + gmtUpdate +
                ", gmtCreate=" + gmtCreate +
                ", founder='" + founder + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }

    public BackstageUsers() {
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}