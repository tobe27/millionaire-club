package com.millionaire.millionairemanagerservice.module;

public class MessageUserPlatform {
    private Long id;
    private Long messagePlatformId;
    private Long uid;
    private Byte look;
    private Long gmtCreate;
    private Long gmtUpdate;

    @Override
    public String toString() {
        return "MessageUserPlatform{" +
                "id=" + id +
                ", messagePlatformId=" + messagePlatformId +
                ", uid=" + uid +
                ", look=" + look +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessagePlatformId() {
        return messagePlatformId;
    }

    public void setMessagePlatformId(Long messagePlatformId) {
        this.messagePlatformId = messagePlatformId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Byte getLook() {
        return look;
    }

    public void setLook(Byte look) {
        this.look = look;
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
}
