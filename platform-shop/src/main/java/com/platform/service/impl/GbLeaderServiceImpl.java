package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.dao.*;
import com.platform.entity.GbLeaderEntity;
import com.platform.service.GbLeaderService;

/**
 * 团长审核表Service实现类
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-09 23:45:08
 */
@Service("gbLeaderService")
public class GbLeaderServiceImpl implements GbLeaderService {
    @Autowired
    private GbLeaderDao gbLeaderDao;

    @Override
    public GbLeaderEntity queryObject(Integer id) {
        return gbLeaderDao.queryObject(id);
    }

    @Override
    public List<GbLeaderEntity> queryList(Map<String, Object> map) {
        return gbLeaderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return gbLeaderDao.queryTotal(map);
    }

    @Override
    public int save(GbLeaderEntity gbLeader) {
        gbLeader.setRegisterTime(new Date());
        return gbLeaderDao.save(gbLeader);
    }

    @Override
    public int update(GbLeaderEntity gbLeader) {
        return gbLeaderDao.update(gbLeader);
    }

    @Override
    public int delete(Integer id) {
        return gbLeaderDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return gbLeaderDao.deleteBatch(ids);
    }
}
