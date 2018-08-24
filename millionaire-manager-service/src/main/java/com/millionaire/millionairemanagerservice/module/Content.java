package com.millionaire.millionairemanagerservice.module;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Content {
    @Null
    private Long id;

    @NotBlank
    private String title;
    @NotNull
    private Byte type;

    private String cover;
    @NotNull
    private String content;
    @NotNull
    private Byte state;
    @NotBlank
    private String editors;
    @Null
    private Long gmtCreate;
    @Null
    private Long gmtUpdate;

    public Content(Long id, String title, Byte type, String cover, String content, Byte state, String editors, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.cover = cover;
        this.content = content;
        this.state = state;
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

    public Byte getType() {
        return type;
    }

    public String getCover() {
        return cover;
    }

    public String getContent() {
        return content;
    }

    public Byte getState() {
        return state;
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
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
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

    public void setType(Byte type) {
        this.type = type;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setState(Byte state) {
        this.state = state;
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

    public Content() {

    }
}