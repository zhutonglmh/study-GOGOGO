package com.zt.study.dao;

import com.zt.study.pojo.ReactUser;

import java.util.List;

public interface ReactUserMapper {


    /**
     * 查
     * @param reactUser
     * @return
     */
    List<ReactUser> findUser(ReactUser reactUser);

    /**
     * 查
     * @param reactUser
     * @return
     */
    List<ReactUser> search(ReactUser reactUser);

    /**
     * 增
     * @param reactUser
     * @return
     */
   int addUser(ReactUser reactUser);

    /**
     * 改
     * @param reactUser
     * @return
     */
   int updateUser(ReactUser reactUser);

    /**
     * 删
     * @param reactUser
     * @return
     */
   int deleteUser(ReactUser reactUser);
}
