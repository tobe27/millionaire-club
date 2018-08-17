package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

@Data
public class MessageUser {
    private Long id;

    private Byte code;

    private Long investmentUserId;

    private Long uid;

    private Byte isLook;

    private Long gmtCreate;

    private Long gmtUpdate;

    public MessageUser() {
    }

    public MessageUser(Long id, Byte code, Long investmentUserId, Long uid, Byte isLook, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.code = code;
        this.investmentUserId = investmentUserId;
        this.uid = uid;
        this.isLook = isLook;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
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