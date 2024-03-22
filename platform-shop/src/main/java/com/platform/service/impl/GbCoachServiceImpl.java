package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.GbCoachDao;
import com.platform.entity.GbCoachEntity;
import com.platform.service.GbCoachService;

/**
 * 教练信息Service实现类
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
@Service("gbCoachService")
public class GbCoachServiceImpl implements GbCoachService {
    @Autowired
    private GbCoachDao gbCoachDao;

    @Override
    public GbCoachEntity queryObject(Integer id) {
        return gbCoachDao.queryObject(id);
    }

    @Override
    public List<GbCoachEntity> queryList(Map<String, Object> map) {
        return gbCoachDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return gbCoachDao.queryTotal(map);
    }

    @Override
    public int save(GbCoachEntity gbCoach) {
        return gbCoachDao.save(gbCoach);
    }

    @Override
    public int update(GbCoachEntity gbCoach) {
        return gbCoachDao.update(gbCoach);
    }

    @Override
    public int delete(Integer id) {
        return gbCoachDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return gbCoachDao.deleteBatch(ids);
    }
}
