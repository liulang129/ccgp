package com.platform.service;

import com.platform.entity.CouponGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 优惠券关联商品Service接口
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-29 21:50:17
 */
public interface CouponGoodsService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CouponGoodsEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CouponGoodsEntity> queryList(Map<String, Object> map);

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
     * @param couponGoods 实体
     * @return 保存条数
     */
    int save(CouponGoodsEntity couponGoods);

    /**
     * 根据主键更新实体
     *
     * @param couponGoods 实体
     * @return 更新条数
     */
    int update(CouponGoodsEntity couponGoods);

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
