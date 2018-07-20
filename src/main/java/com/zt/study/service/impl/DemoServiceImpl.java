package com.zt.study.service.impl;

import com.zt.study.dao.DsxhOrderMapper;
import com.zt.study.dao.ReactUserMapper;
import com.zt.study.pojo.DsxhOrder;
import com.zt.study.pojo.ReactUser;
import com.zt.study.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DsxhOrderMapper dsxhOrderMapper;

    @Resource
    private ReactUserMapper reactUserMapper;
    @Override
    public List<DsxhOrder> findDataForPage(DsxhOrder dsxhOrder) {

        List<DsxhOrder> list = null;
        try {
            list = dsxhOrderMapper.findDataForPage(dsxhOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 新增
     *
     * @param reactUser
     * @return
     */
    @Override
    @Transactional
    public int saveUser(ReactUser reactUser) {

        reactUser.setId(UUID.randomUUID().toString());
        reactUser.setCreateTime(new Date());
        return reactUserMapper.addUser(reactUser);
    }

    /**
     * 修改
     *
     * @param reactUser
     * @return
     */
    @Override
    @Transactional
    public int updateUser(ReactUser reactUser) {
        return reactUserMapper.updateUser(reactUser);
    }

    /**
     * 删除
     *
     * @param reactUser
     * @return
     */
    @Override
    @Transactional
    public int deleteUser(ReactUser reactUser) {
        return reactUserMapper.deleteUser(reactUser);
    }

    /**
     * 查
     *
     * @param reactUser
     * @return
     */
    @Override
    public List<ReactUser> query(ReactUser reactUser) {

        if(null == reactUser.getName() || "".equals(reactUser.getName()) ){
            return reactUserMapper.findUser(reactUser);
        }
        return reactUserMapper.search(reactUser);
    }
}
