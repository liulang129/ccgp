package com.platform.service;

import com.platform.entity.GbCoachLevelEntity;

import java.util.List;
import java.util.Map;

/**
 * 教练级别Service接口
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 10:57:45
 */
public interface GbCoachLevelService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    GbCoachLevelEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<GbCoachLevelEntity> queryList(Map<String, Object> map);

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
     * @param gbCoachLevel 实体
     * @return 保存条数
     */
    int save(GbCoachLevelEntity gbCoachLevel);

    /**
     * 根据主键更新实体
     *
     * @param gbCoachLevel 实体
     * @return 更新条数
     */
    int update(GbCoachLevelEntity gbCoachLevel);

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
