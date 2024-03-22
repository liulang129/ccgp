package com.platform.dao;

import com.platform.entity.GoodsGalleryEntity;

import java.util.Map;

/**
 * Dao
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-23 14:41:43
 */
public interface GoodsGalleryDao extends BaseDao<GoodsGalleryEntity> {
    int deleteByGoodsId(Map<String, Integer> map);
}
