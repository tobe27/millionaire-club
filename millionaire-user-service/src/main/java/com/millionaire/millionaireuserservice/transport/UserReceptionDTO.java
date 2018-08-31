package com.millionaire.millionaireuserservice.transport;

public class UserReceptionDTO {
    private Long id;
    private String idName;
    private String idNumber;
    private Long phone;
    private String bankName;
    private String bankCardNumber;
    private String email;
    private String address;

    @Override
    public String toString() {
        return "UserReceptionDTO{" +
                "id=" + id +
                ", idName='" + idName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phone=" + phone +
                ", bankName='" + bankName + '\'' +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
