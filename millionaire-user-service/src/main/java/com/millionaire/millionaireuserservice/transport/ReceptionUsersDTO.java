package com.millionaire.millionaireuserservice.transport;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/20 21:46
 */

public class ReceptionUsersDTO {
    private ReceptionUsers users;
    private List<UserBank> listUserBank;


    public ReceptionUsersDTO() {
    }

    @Override
    public String toString() {
        return "ReceptionUsersDTO{" +
                "users=" + users +
                ", listUserBank=" + listUserBank +
                '}';
    }

    public ReceptionUsers getUsers() {
        return users;
    }

    public ReceptionUsersDTO setUsers(ReceptionUsers users) {
        this.users = users;
        return this;
    }

    public List<UserBank> getListUserBank() {
        return listUserBank;
    }

    public ReceptionUsersDTO setListUserBank(List<UserBank> listUserBank) {
        this.listUserBank = listUserBank;
        return this;
    }
}
