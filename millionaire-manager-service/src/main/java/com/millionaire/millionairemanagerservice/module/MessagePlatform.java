package com.millionaire.millionairemanagerservice.module;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class MessagePlatform {
    @Null
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private Byte sendingCrowd;
    @NotBlank
    private String brief;
    @NotBlank
    private String content;
    @NotNull
    private Byte type;

    private Long timingTime;
    @NotNull
    private Byte status;
    @NotNull
    private String editors;
    @Null
    private Long gmtCreate;
    @Null
    private Long gmtUpdate;

    public MessagePlatform(Long id, String title, Byte sendingCrowd, String brief, String content, Byte type, Long timingTime, Byte status, String editors, Long gmtCreate, Long gmtUpdate) {
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

    public Long getTimingTime() {
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

    @Override
    public String toString() {
        return "MessagePlatform{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sendingCrowd=" + sendingCrowd +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", timingTime=" + timingTime +
                ", status=" + status +
                ", editors='" + editors + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSendingCrowd(Byte sendingCrowd) {
        this.sendingCrowd = sendingCrowd;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setTimingTime(Long timingTime) {
        this.timingTime = timingTime;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public MessagePlatform() {

    }
}