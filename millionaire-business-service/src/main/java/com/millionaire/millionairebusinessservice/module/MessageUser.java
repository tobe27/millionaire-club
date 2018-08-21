package com.millionaire.millionairebusinessservice.module;

public class MessageUser {
    private Long id;

    private Byte code;

    private Long investmentUserId;

    private Long uid;

    private Byte isLook;

    private Long gmtCreate;

    private Long gmtUpdate;

    public MessageUser(Long id, Byte code, Long investmentUserId, Long uid, Byte isLook, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.code = code;
        this.investmentUserId = investmentUserId;
        this.uid = uid;
        this.isLook = isLook;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "MessageUser{" +
                "id=" + id +
                ", code=" + code +
                ", investmentUserId=" + investmentUserId +
                ", uid=" + uid +
                ", isLook=" + isLook +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public void setInvestmentUserId(Long investmentUserId) {
        this.investmentUserId = investmentUserId;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setIsLook(Byte isLook) {
        this.isLook = isLook;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public MessageUser() {

    }

    public Long getId() {
        return id;
    }

    public Byte getCode() {
        return code;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public Long getUid() {
        return uid;
    }

    public Byte getIsLook() {
        return isLook;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}