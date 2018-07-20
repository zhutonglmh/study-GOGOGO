package com.zt.study.jdbc;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * 生产加工生产计划主表实体类
 * <p>
 * Create By zhutong On 2018/07/02 15:13:42
 *
 * @author zhutong
 */
@Data
public class Entity {
    /**
     * 配送中心ID
     */
    private String distribId;

    /**
     * 配送中心名称
     */
    private String distribName;
    /**
     * 加工间ID
     */
    private String processId;

    /**
     * 加工间名称
     */
    private String processName;
    /**
     * 单据号
     */
    private String billNo;
    /**
     * 生产需求单ID
     */
    private String demandOrderId;
    /**
     * 生产需求单号
     */
    private String demandOrderBillNo;
    /**
     * 状态0未领料1领料中2生产中3生产结束4已作废
     */
    private Integer status;


    /**
     * 起始时间(查询使用)
     */
    private Date startDate;

    /**
     * 结束时间(查询使用)
     */
    private Date endDate;

    /**
     * 入库仓库
     */
    private String depotName;

    /**
     * 修改人名称
     */
    private String updateUserName;

    /**
     * 生产计划单中未领料产品数量
     */
    private Integer notPickingCount;

    /**
     * 1代表门店请购，2代表手动添加
     */
    private Integer billSource;

}

