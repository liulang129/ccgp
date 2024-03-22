package com.platform.dao;

import java.util.List;
import java.util.Map;

import com.platform.entity.GroupBuyingEntity;
import com.platform.entity.OrderEntity;

/**
 * 
 * 
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-13 10:41:09
 */
public interface OrderDao extends BaseDao<OrderEntity> {
    List<GroupBuyingEntity> queryGroupList(Map<String, Object> map);
    int queryGroupTotal(Map<String, Object> map);
	
}
