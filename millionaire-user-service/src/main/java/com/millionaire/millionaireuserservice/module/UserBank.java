package com.millionaire.millionaireuserservice.module;

public class UserBank {
    private Long id;

    private String name;

    private String idNumber;

    private String city;

    private String bankName;

    private String cardNumber;

    private String cardType;

    private String bankPhone;

    private Long uid;

    private Long gmtCreate;

    private Long gmtUpdate;

    public UserBank(Long id, String name, String idNumber, String city, String bankName, String cardNumber, String cardType, String bankPhone, Long uid, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.city = city;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.bankPhone = bankPhone;
        this.uid = uid;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getCity() {
        return city;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public Long getUid() {
        return uid;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    @Override
    public String toString() {
        return "UserBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", city='" + city + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", bankPhone='" + bankPhone + '\'' +
                ", uid=" + uid +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public UserBank() {

    }
}