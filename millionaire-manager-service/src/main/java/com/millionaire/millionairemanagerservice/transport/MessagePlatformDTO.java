package com.millionaire.millionairemanagerservice.transport;

public class MessagePlatformDTO {
    private Long id;
    private String brief;
    private Byte look;
    private Long gmtCreate;
    private Long messageUserId;

    @Override
    public String toString() {
        return "MessagePlatformDTO{" +
                "id=" + id +
                ", brief='" + brief + '\'' +
                ", look=" + look +
                ", gmtCreate=" + gmtCreate +
                ", messageUserId=" + messageUserId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
        this.messageUserId = messageUserId;
    }
}
