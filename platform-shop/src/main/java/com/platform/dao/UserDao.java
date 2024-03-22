package com.platform.dao;

import com.platform.entity.UserEntity;

/**
 * 会员Dao
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-16 15:02:28
 */
public interface UserDao extends BaseDao<UserEntity> {

    void updatePromoter(UserEntity user);
}
