package com.millionaire.millionairemanagerservice.request;



/**
 * @author Liu Kai
 * @Description: TODO 用于内容查询参数接收
 * @date 2018/8/23 8:59
 */

public class ContentQuery {
    private String title;
    private Byte state;
    private Byte type;
    private String editors;
    private Long lowerDate;
    private Long upperDate;

    public ContentQuery() {
    }

    @Override
    public String toString() {
        return "ContentQuery{" +
                "title='" + title + '\'' +
                ", state=" + state +
                ", type=" + type +
                ", editors='" + editors + '\'' +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                '}';
    }

    public Byte getType() {
        return type;
    }

    public ContentQuery setType(Byte type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ContentQuery setTitle(String title) {
        this.title = title;
        return this;
    }


    public Byte getState() {
        return state;
    }

    public ContentQuery setState(Byte state) {
        this.state = state;
        return this;
    }

    public String getEditors() {
        return editors;
    }

    public ContentQuery setEditors(String editors) {
        this.editors = editors;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public ContentQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public ContentQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }
}
