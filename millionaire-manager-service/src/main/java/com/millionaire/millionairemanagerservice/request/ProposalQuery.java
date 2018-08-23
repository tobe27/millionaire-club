package com.millionaire.millionairemanagerservice.request;


/**
 * @author Liu Kai
 * @Description: TODO 反馈信息查看参数包装
 * @date 2018/8/23 13:33
 */

public class ProposalQuery {

    private String name;
    private Long phone;
    private String email;
    private Long lowerGmtCreate;
    private Long upperGmtCreate;


    public ProposalQuery() {
    }

    @Override
    public String toString() {
        return "ProposalQuery{" +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", lowerGmtCreate=" + lowerGmtCreate +
                ", upperGmtCreate=" + upperGmtCreate +
                '}';
    }

    public String getName() {
        return name;
    }

    public ProposalQuery setName(String name) {
        this.name = name;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public ProposalQuery setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProposalQuery setEmail(String email) {
        this.email = email;
        return this;
    }


    public Long getLowerGmtCreate() {
        return lowerGmtCreate;
    }

    public ProposalQuery setLowerGmtCreate(Long lowerGmtCreate) {
        this.lowerGmtCreate = lowerGmtCreate;
        return this;
    }

    public Long getUpperGmtCreate() {
        return upperGmtCreate;
    }

    public ProposalQuery setUpperGmtCreate(Long upperGmtCreate) {
        this.upperGmtCreate = upperGmtCreate;
        return this;
    }
}