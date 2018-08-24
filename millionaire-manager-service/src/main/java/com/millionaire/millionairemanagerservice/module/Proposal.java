package com.millionaire.millionairemanagerservice.module;

public class Proposal {
    private Long id;

    private String phone;

    private String name;

    private String email;

    private String proposal;

    private Long gmtCreate;

    private Long gmtUpdate;

    public Proposal(Long id, String phone, String name, String email, String proposal, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.proposal = proposal;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProposal() {
        return proposal;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", proposal='" + proposal + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Proposal() {

    }
}