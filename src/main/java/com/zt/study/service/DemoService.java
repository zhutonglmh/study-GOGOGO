package com.zt.study.service;

import com.zt.study.pojo.DsxhOrder;
import com.zt.study.pojo.ReactUser;

import java.util.List;

public interface DemoService {

    List<DsxhOrder> findDataForPage(DsxhOrder dsxhOrder);

    /**
     * 新增
     * @param reactUser
     * @return
     */
    int saveUser(ReactUser reactUser);

    /**
     * 修改
     * @param reactUser
     * @return
     */
    int updateUser(ReactUser reactUser);

    /**
     * 删除
     * @param reactUser
     * @return
     */
    int deleteUser(ReactUser reactUser);

    /**
     * 查
     * @param reactUser
     * @return
     */
    List<ReactUser> query(ReactUser reactUser);
}
