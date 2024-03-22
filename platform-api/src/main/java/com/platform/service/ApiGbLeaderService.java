package com.platform.service;

import com.platform.entity.GbLeaderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiGbLeaderMapper;
import com.platform.entity.GbLeaderVo;
import com.platform.service.ApiGbLeaderService;

/**
 * 团长审核表Service实现类
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-09 23:45:08
 */
@Service
public class ApiGbLeaderService   {
    @Autowired
    private ApiGbLeaderMapper gbLeaderMapper;

    public int save(GbLeaderVo gbLeader) {
        return gbLeaderMapper.save(gbLeader);
    }

}
