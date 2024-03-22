package com.platform.dao;

import java.util.List;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;

/**
 * 
 * 
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiOrderGoodsMapper extends BaseDao<OrderGoodsVo> {
	List<OrderGoodsVo> queryInvalidOrder();
}
