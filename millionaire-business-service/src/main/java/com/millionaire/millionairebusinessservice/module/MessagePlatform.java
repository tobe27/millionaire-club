package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

import java.util.Date;

@Data
public class MessagePlatform {
    private Long id;

    private String title;

    private Byte sendingCrowd;

    private String brief;

    private String content;

    private Byte type;

    private Date timingTime;

    private Byte status;

    private String editors;

    private Long gmtCreate;

    private Long gmtUpdate;

    public MessagePlatform() {
    }

    public MessagePlatform(Long id, String title, Byte sendingCrowd, String brief, String content, Byte type, Date timingTime, Byte status, String editors, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.title = title;
        this.sendingCrowd = sendingCrowd;
        this.brief = brief;
        this.content = content;
        this.type = type;
        this.timingTime = timingTime;
        this.status = status;
        this.editors = editors;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Byte getSendingCrowd() {
        return sendingCrowd;
    }

    public String getBrief() {
        return brief;
    }

    public String getContent() {
        return content;
    }

    public Byte getType() {
        return type;
    }

    public Date getTimingTime() {
        return timingTime;
    }

    public Byte getStatus() {
        return status;
    }

    public String getEditors() {
        return editors;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}