package com.millionaire.millionairepaymentmanager.fuyou;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderReqDataTest {

    @Test
    public void getLogotp() {
        OrderReqData orderReqData = new OrderReqData();
        orderReqData.setVersion("22");
        orderReqData.setMchntcd("2");
        orderReqData.setType("2");
        orderReqData.setMchntorderid("2");
        orderReqData.setUserid("2");
        orderReqData.setAmt("2");
        orderReqData.setBankcard("2");
        orderReqData.setName("2");
        orderReqData.setBackurl("2");
        orderReqData.setIdtype("2");
        orderReqData.setIdno("2");
        orderReqData.setRem1("2");
        orderReqData.setRem2("2");
        orderReqData.setRem3("2");
        orderReqData.setSigntp("2");
        orderReqData.setSign("2");
        orderReqData.setLogotp("2");
        orderReqData.setReurl("2");
        orderReqData.setHomeurl("2");

        System.out.println(orderReqData);

    }
}