package com.platform.service;

import com.platform.entity.GbLeaderEntity;

import java.util.List;
import java.util.Map;

/**
 * 团长审核表Service接口
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-09 23:45:08
 */
public interface GbLeaderService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    GbLeaderEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<GbLeaderEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param gbLeader 实体
     * @return 保存条数
     */
    int save(GbLeaderEntity gbLeader);

    /**
     * 根据主键更新实体
     *
     * @param gbLeader 实体
     * @return 更新条数
     */
    int update(GbLeaderEntity gbLeader);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
