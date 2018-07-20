package com.zt.study.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DsxhOrder {

    /**
     * 主键
     */
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 单号
     */
    private String billNo;

    /**
     * 实付金额
     */
    private BigDecimal priceAmt;

    /**
     * 订单状态
     */
    private Integer status;   //0 未支付   1  已支付  2 审核退款  4 已退款
    /**
     * 课程单价
     */
    private BigDecimal price;


    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 字符串时间
     */
    private String timeStr;

}