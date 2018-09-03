package com.millionaire.millionairebusinessservice.transport;

public class InvestmentUsersDTO {
    private Long id;
    private String name; //产品名称
    private Double annualizedIncome; //年化收益
    private Integer investmentAmount; //投资金额
    private Byte investmentStatus;  //项目状态
    private Long valueDateStart;   //起息时间
    private Long valueDateEnd;  //到息时间

    @Override
    public String toString() {
        return "InvestmentUsersDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualizedIncome=" + annualizedIncome +
                ", investmentAmount=" + investmentAmount +
                ", investmentStatus=" + investmentStatus +
                ", valueDateStart=" + valueDateStart +
                ", valueDateEnd=" + valueDateEnd +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAnnualizedIncome() {
        return annualizedIncome;
    }

    public void setAnnualizedIncome(Double annualizedIncome) {
        this.annualizedIncome = annualizedIncome;
    }

    public Integer getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Byte getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(Byte investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public Long getValueDateStart() {
        return valueDateStart;
    }

    public void setValueDateStart(Long valueDateStart) {
        this.valueDateStart = valueDateStart;
    }

    public Long getValueDateEnd() {
        return valueDateEnd;
    }

    public void setValueDateEnd(Long valueDateEnd) {
        this.valueDateEnd = valueDateEnd;
    }
}
