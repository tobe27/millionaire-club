package com.millionaire.millionaireadminservice.module;

import java.util.List;

public class Modular {
    private Long id;
    private String name;
    private List<Permissions> permissionsList;

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

    public List<Permissions> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<Permissions> permissionsList) {
        this.permissionsList = permissionsList;
    }

    @Override
    public String toString() {
        return "Modular{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissionsList=" + permissionsList +
                '}';
    }
}
