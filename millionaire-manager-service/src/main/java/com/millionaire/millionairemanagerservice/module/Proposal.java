package com.millionaire.millionairemanagerservice.module;

public class Proposal {
    private Long id;

    private Long uid;

    private String proposal;

    private Long gmtCreate;

    private Long gmtUpdate;

    public Proposal(Long id, Long uid, String proposal, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.uid = uid;
        this.proposal = proposal;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Proposal() {
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", uid=" + uid +
                ", proposal='" + proposal + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Proposal setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public Proposal setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getProposal() {
        return proposal;
    }

    public Proposal setProposal(String proposal) {
        this.proposal = proposal;
        return this;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Proposal setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public Proposal setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
        return this;
    }
}