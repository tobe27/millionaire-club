package com.millionaire.millionaireuserservice.transport;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 用户信息详细查询参数传递
 * @date 2018/8/20 21:46
 */
@Data
public class ReceptionUsersDTO {
    private Long id;

    private String userNumber;

    private Long phone;

    private String password;

    private String salt;

    private String managerNumber;

    private String email;

    private String idName;

    private String idNumber;

    private String idFront;

    private String idBack;

    private String address;


    private Long applicationTime;

    private String refusal;

    private Integer assets;

    private Integer profit;

    private String logo;

    private Byte status;

    private Byte idAuthentication;

    private Long gmtCreate;

    private Long gmtUpdate;

    private Long loginTime;

    List bankId = new ArrayList();



}
