package com.millionaire.millionaireuserservice.module;

import org.springframework.stereotype.Component;

/**
 * @author Liu Kai
 * @Description: TODO 用于接收用户查询传入的相关参数
 * @date 2018/8/20 12:46
 */
public class ReceptionUsersQuery {

    private String idName;
    private String phone;
    private String managerNumber;
    private Byte status;
    private Long lowerDate;
    private Long upperDate;



    @Override
    public String toString() {
        return "ReceptionUsersQuery{" +
                "idName='" + idName + '\'' +
                ", phone='" + phone + '\'' +
                ", managerNumber='" + managerNumber + '\'' +
                ", status=" + status +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                '}';
    }

    public String getIdName() {
        return idName;
    }

    public ReceptionUsersQuery setIdName(String idName) {
        this.idName = idName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ReceptionUsersQuery setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getManagerNumber() {
        return managerNumber;
    }

    public ReceptionUsersQuery setManagerNumber(String managerNumber) {
        this.managerNumber = managerNumber;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public ReceptionUsersQuery setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public ReceptionUsersQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public ReceptionUsersQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }
}
