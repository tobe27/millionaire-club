package com.millionaire.millionairemanagerservice.transport;

/**
 * @author Liu Kai
 * @Description: TODO 用户意见反馈连表查询接收类
 * @date 2018/8/23 17:38
 */

public class UserProposalDTO {

    private Long id;
    private String name;
    private String proposal;

    private Long phone;
    private String email;
    private Long gmtCreate;

    public UserProposalDTO() {
    }

    @Override
    public String toString() {
        return "UserProposalDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", proposal='" + proposal + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }

    public String getProposal() {
        return proposal;
    }

    public UserProposalDTO setProposal(String proposal) {
        this.proposal = proposal;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProposalDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserProposalDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public UserProposalDTO setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProposalDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public UserProposalDTO setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
}
