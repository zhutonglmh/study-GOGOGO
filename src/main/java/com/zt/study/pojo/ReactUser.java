package com.zt.study.pojo;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ReactUser {

    private String id;

    private String name;

    private Integer age;

    private String sex;

    private String iphone;

    private String address;

    private String email;

    private Date createTime;

    private List<ReactUserDetail> reactUserDetails;
}
