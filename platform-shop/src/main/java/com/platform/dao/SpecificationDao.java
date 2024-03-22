package com.platform.dao;

import java.util.List;

import com.platform.entity.SpecificationEntity;

/**
 * 规格表
 * 
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-13 10:41:10
 */
public interface SpecificationDao extends BaseDao<SpecificationEntity> {
	List<SpecificationEntity> queryListByGoodsId(String goodsId);
	
}
