package com.millionaire.millionaireuserservice.module;

public class ReceptionUsers {
    private Long id;

    private String userNumber;

    private String phone;

    private String password;

    private String salt;

    private String managerNumber;

    private String email;

    private String idName;

    private String idNumber;

    private String idFront;

    private String idBack;

    private String address;

    private Long bankId;

    private Long applicationTime;

    private String refusal;

    private Double assets;

    private Double profit;

    private String logo;

    private Byte status;

    private Byte idAuthentication;

    private Long gmtCreate;

    private Long gmtUpdate;

    private Long loginTime;



    public ReceptionUsers() {
    }


    @Override
    public String toString() {
        return "ReceptionUsers{" +
                "id=" + id +
                ", userNumber='" + userNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", managerNumber='" + managerNumber + '\'' +
                ", email='" + email + '\'' +
                ", idName='" + idName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", idFront='" + idFront + '\'' +
                ", idBack='" + idBack + '\'' +
                ", address='" + address + '\'' +
                ", bankId=" + bankId +
                ", applicationTime=" + applicationTime +
                ", refusal='" + refusal + '\'' +
                ", assets=" + assets +
                ", profit=" + profit +
                ", logo='" + logo + '\'' +
                ", status=" + status +
                ", idAuthentication=" + idAuthentication +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                ", loginTime=" + loginTime +
                '}';
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(String managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdBack() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Long applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getRefusal() {
        return refusal;
    }

    public void setRefusal(String refusal) {
        this.refusal = refusal;
    }

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIdAuthentication() {
        return idAuthentication;
    }

    public void setIdAuthentication(Byte idAuthentication) {
        this.idAuthentication = idAuthentication;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
}