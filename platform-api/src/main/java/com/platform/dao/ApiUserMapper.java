package com.platform.dao;

import com.platform.entity.SmsLogVo;
import com.platform.entity.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-03-23 15:22:06
 */
public interface ApiUserMapper extends BaseDao<UserVo> {
    UserVo queryByMobile(@Param("mobile") String mobile);

    UserVo queryByOpenId(@Param("openId") String openId);

    /**
     * 获取用户最后一条短信
     *
     * @param user_id
     * @return
     */
    SmsLogVo querySmsCodeByUserId(@Param("user_id") Long user_id);

    /**
     * 保存短信
     *
     * @param smsLogVo
     * @return
     */
    int saveSmsCodeLog(SmsLogVo smsLogVo);
    
    /**
     * 更新分销比例
     * @param vo
     * @return
     */
    int updatefx(UserVo vo);
    
}

