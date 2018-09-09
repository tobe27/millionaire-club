package com.millionaire.millionairebusinessservice.module;

public class MessageUser {
    private Long id;

//    代号
    private Byte code;

//    用户投资id
    private Long investmentUserId;

//    用户id
    private Long uid;

//    是否有观看过信息
    private Byte isLook;

    private Long gmtCreate;

    private Long gmtUpdate;

    private Long messagePlatformId;

    public MessageUser() {
    }

    public MessageUser(Long id, Byte code, Long investmentUserId, Long uid, Byte isLook, Long gmtCreate, Long gmtUpdate, Long messagePlatformId) {

        this.id = id;
        this.code = code;
        this.investmentUserId = investmentUserId;
        this.uid = uid;
        this.isLook = isLook;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.messagePlatformId = messagePlatformId;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public void setInvestmentUserId(Long investmentUserId) {
        this.investmentUserId = investmentUserId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Byte getIsLook() {
        return isLook;
    }

    public void setIsLook(Byte isLook) {
        this.isLook = isLook;
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

    public Long getMessagePlatformId() {
        return messagePlatformId;
    }

    public void setMessagePlatformId(Long messagePlatformId) {
        this.messagePlatformId = messagePlatformId;
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
                ", messagePlatformId=" + messagePlatformId +
                '}';
    }
}