package com.millionaire.millionairebusinessservice.transport;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/27 13:00
 */
@Data
public class ClaimMatchDTO {
//      private InvestmentProduct investmentProduct;
//      private ClaimInfo claimInfo;
//    private InvestmentUser investmentUser;


    private Long valueDateStart;
    private Long valueDateEnd;
    private String userName;
    private String productName;
    private String lendingContractNumber;
    private Integer investmentAmount;
    private String creditContractNumber;

}
