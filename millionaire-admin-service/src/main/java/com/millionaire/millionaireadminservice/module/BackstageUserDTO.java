package com.millionaire.millionaireadminservice.module;

public class BackstageUserDTO {
    private Long id;
    private String name;
    private String roleName;
    private String phone;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "BackstageUserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
