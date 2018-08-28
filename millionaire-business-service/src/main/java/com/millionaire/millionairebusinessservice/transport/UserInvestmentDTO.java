package com.millionaire.millionairebusinessservice.transport;

public class UserInvestmentDTO {
    private Long id;
    private String name;     //产品名称
    private Double annualizedIncome;      //年化收益率
    private Double expectedIncome;   //预期收益
    private Integer investmentAmount;    //投资金额
    private Long valueDateStart;    //起息时间
    private Long valueDateEnd;  //到期时间
    private Byte repaymentMode;  //还款方式
    private Integer startingAmount;  //起投金额
    private Byte investmentStatus;   //投资状态
    private Double distributedIncome;  //已分配收益
    private Long uid; //用户id

    @Override
    public String toString() {
        return "InvestmentUserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualizedIncome=" + annualizedIncome +
                ", expectedIncome=" + expectedIncome +
                ", investmentAmount=" + investmentAmount +
                ", valueDateStart=" + valueDateStart +
                ", valueDateEnd=" + valueDateEnd +
                ", repaymentMode=" + repaymentMode +
                ", startingAmount=" + startingAmount +
                ", investmentStatus=" + investmentStatus +
                ", distributedIncome=" + distributedIncome +
                ", uid=" + uid +
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

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Integer getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        this.investmentAmount = investmentAmount;
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

    public Byte getRepaymentMode() {
        return repaymentMode;
    }

    public void setRepaymentMode(Byte repaymentMode) {
        this.repaymentMode = repaymentMode;
    }

    public Integer getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(Integer startingAmount) {
        this.startingAmount = startingAmount;
    }

    public Byte getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(Byte investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public Double getDistributedIncome() {
        return distributedIncome;
    }

    public void setDistributedIncome(Double distributedIncome) {
        this.distributedIncome = distributedIncome;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
