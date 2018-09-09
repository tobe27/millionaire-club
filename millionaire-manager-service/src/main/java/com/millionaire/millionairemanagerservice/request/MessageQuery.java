package com.millionaire.millionairemanagerservice.request;

public class MessageQuery {
    private Byte sendingCrowd;
    private Long id;

    @Override
    public String toString() {
        return "MessageQuery{" +
                "sendingCrowd=" + sendingCrowd +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSendingCrowd() {

        return sendingCrowd;
    }

    public void setSendingCrowd(Byte sendingCrowd) {
        this.sendingCrowd = sendingCrowd;
    }
}
