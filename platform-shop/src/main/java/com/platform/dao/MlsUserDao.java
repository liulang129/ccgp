package com.platform.dao;

import com.platform.entity.MlsUserEntity2;

/**
 * 会员Dao
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-16 15:02:28
 */
public interface MlsUserDao extends BaseDao<MlsUserEntity2> {

    int updatefx(MlsUserEntity2 user);
}
