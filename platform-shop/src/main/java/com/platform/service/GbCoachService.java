package com.platform.service;

import com.platform.entity.GbCoachEntity;

import java.util.List;
import java.util.Map;

/**
 * 教练信息Service接口
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
public interface GbCoachService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    GbCoachEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<GbCoachEntity> queryList(Map<String, Object> map);

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
     * @param gbCoach 实体
     * @return 保存条数
     */
    int save(GbCoachEntity gbCoach);

    /**
     * 根据主键更新实体
     *
     * @param gbCoach 实体
     * @return 更新条数
     */
    int update(GbCoachEntity gbCoach);

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
