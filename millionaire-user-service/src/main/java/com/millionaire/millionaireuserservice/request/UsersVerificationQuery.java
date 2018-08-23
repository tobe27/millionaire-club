package com.millionaire.millionaireuserservice.request;

public class UsersVerificationQuery {

    private String idName;
    private String phone;
    private Long lowerDate;
    private Long upperDate;
    private String userNumber;
    private Byte idAuthentication;


    public UsersVerificationQuery() {
    }

    @Override
    public String toString() {
        return "UsersVerificationQuery{" +
                "idName='" + idName + '\'' +
                ", phone='" + phone + '\'' +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                ", userNumber='" + userNumber + '\'' +
                ", idAuthentication=" + idAuthentication +
                '}';
    }

    public String getIdName() {
        return idName;
    }

    public UsersVerificationQuery setIdName(String idName) {
        this.idName = idName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UsersVerificationQuery setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public UsersVerificationQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public UsersVerificationQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public UsersVerificationQuery setUserNumber(String userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public Byte getIdAuthentication() {
        return idAuthentication;
    }

    public UsersVerificationQuery setIdAuthentication(Byte idAuthentication) {
        this.idAuthentication = idAuthentication;
        return this;
    }
}
