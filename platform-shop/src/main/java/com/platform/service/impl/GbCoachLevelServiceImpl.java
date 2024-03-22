package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.GbCoachLevelDao;
import com.platform.entity.GbCoachLevelEntity;
import com.platform.service.GbCoachLevelService;

/**
 * 教练级别Service实现类
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 10:57:45
 */
@Service("gbCoachLevelService")
public class GbCoachLevelServiceImpl implements GbCoachLevelService {
    @Autowired
    private GbCoachLevelDao gbCoachLevelDao;

    @Override
    public GbCoachLevelEntity queryObject(Integer id) {
        return gbCoachLevelDao.queryObject(id);
    }

    @Override
    public List<GbCoachLevelEntity> queryList(Map<String, Object> map) {
        return gbCoachLevelDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return gbCoachLevelDao.queryTotal(map);
    }

    @Override
    public int save(GbCoachLevelEntity gbCoachLevel) {
        return gbCoachLevelDao.save(gbCoachLevel);
    }

    @Override
    public int update(GbCoachLevelEntity gbCoachLevel) {
        return gbCoachLevelDao.update(gbCoachLevel);
    }

    @Override
    public int delete(Integer id) {
        return gbCoachLevelDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return gbCoachLevelDao.deleteBatch(ids);
    }
}
