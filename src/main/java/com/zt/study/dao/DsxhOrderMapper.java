package com.zt.study.dao;


import com.zt.study.pojo.DsxhOrder;

import java.util.List;

public interface DsxhOrderMapper {

    /**
     * 查询订单是否有
     * @param dsxhOrder
     * @return
     * @throws Exception
     */
    List<DsxhOrder> getUsedOrder(DsxhOrder dsxhOrder) throws Exception;

    List<DsxhOrder> findDataForPage(DsxhOrder dsxhOrder) throws Exception;
}