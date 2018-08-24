package com.millionaire.millionaireserverweb.request;


/**
 * @author Liu Kai
 * @Description: TODO 意见反馈连表查询参数接收类
 * @date 2018/8/23 15:24
 */

public class UserProposalQuery {

    private String phone;

    private String name;

    private String email;

    private Long lowerGmtCreate;

    private Long upperGmtCreate;

    public UserProposalQuery() {
    }

    @Override
    public String toString() {
        return "UserProposalQuery{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lowerGmtCreate=" + lowerGmtCreate +
                ", upperGmtCreate=" + upperGmtCreate +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public UserProposalQuery setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserProposalQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProposalQuery setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getLowerGmtCreate() {
        return lowerGmtCreate;
    }

    public UserProposalQuery setLowerGmtCreate(Long lowerGmtCreate) {
        this.lowerGmtCreate = lowerGmtCreate;
        return this;
    }

    public Long getUpperGmtCreate() {
        return upperGmtCreate;
    }

    public UserProposalQuery setUpperGmtCreate(Long upperGmtCreate) {
        this.upperGmtCreate = upperGmtCreate;
        return this;
    }
}
