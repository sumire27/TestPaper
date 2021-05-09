package cn.itcast.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String userId;
    private String userName;
    private String userPwd;
    private String grade;
    private Integer userType;
    private Integer userState;
    private String email;
    private String telephone;
    private String address;
    private String remark;

}
