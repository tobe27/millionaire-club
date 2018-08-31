package com.millionaire.millionairebusinessservice.transport;

public class UserMessageDTO {
    private Long id;
    private Byte code;  //10 投资成功 20 投资失败 30 即将到期 40 正在回款 50 回款成功 60 回款失败
    private String name;  //产品名
    private Long gmtCreate;   //时间
    private Boolean look;  //用户是否查看
    private Long investmentUserId; //用户投资id

    @Override
    public String toString() {
        return "UserMessageDTO{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", look=" + look +
                ", investmentUserId=" + investmentUserId +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getLook() {
        return look;
    }

    public void setLook(Boolean look) {
        this.look = look;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public void setInvestmentUserId(Long investmentUserId) {
        this.investmentUserId = investmentUserId;
    }
}
