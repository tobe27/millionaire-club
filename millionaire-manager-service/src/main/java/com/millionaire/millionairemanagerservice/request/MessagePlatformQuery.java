package com.millionaire.millionairemanagerservice.request;

import java.util.Date;

/**
 * @author Liu Kai
 * @Description: TODO 平台消息查看参数接收类
 * @date 2018/8/23 14:05
 */

public class MessagePlatformQuery {

    private String title;

    private Byte type;

    private Byte status;

    private String editors;

    private Long lowerDate;

    private Long upperDate;

    public MessagePlatformQuery() {
    }

    @Override
    public String toString() {
        return "MessagePlatformQuery{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", editors='" + editors + '\'' +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public MessagePlatformQuery setTitle(String title) {
        this.title = title;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public MessagePlatformQuery setType(Byte type) {
        this.type = type;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public MessagePlatformQuery setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public String getEditors() {
        return editors;
    }

    public MessagePlatformQuery setEditors(String editors) {
        this.editors = editors;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public MessagePlatformQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public MessagePlatformQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }
}
