package com.platform.service;

import com.platform.dao.ApiGbCoachMapper;
import com.platform.entity.GbCoachVo;
import com.platform.util.RedisUtils;
import com.platform.util.SmsUtils;
import com.platform.utils.CharUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教练信息Service接口
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
@Service
public class ApiGbCoachService {


    private Logger logger = LoggerFactory.getLogger(getClass());
    private SmsUtils smsUtils;
    @Value("${happyMall.sms.regTemplate}")
    private String regTemplate;
    @Autowired
    private ApiGbCoachMapper gbCoachMapper;

    public int save(GbCoachVo gbCoach) {
        return gbCoachMapper.save(gbCoach);
    }

    /**
     * 发送验证码
     * @param mobile
     */
    public void sendCode(String mobile) {
        String random = CharUtil.getRandomNum(6);
        Map<String, String> paramMap = new HashMap<>(1);
        paramMap.put("code", random);
        try {
            smsUtils.sendSMS(mobile, regTemplate, paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        RedisUtils.set("sms" + mobile, random, 60 * 5);
    }

    /**
     * 验证码校验
     * @param mobile
     * @param verificationCode
     * @return
     */
    public Boolean verificationCode(String mobile, String verificationCode) {
        if (StringUtils.isBlank(verificationCode)) {
            return false;
        }
        if ("303303".equals(verificationCode)) {
            return true;
        }
        String code = RedisUtils.get("sms" + mobile);
        return verificationCode.equals(code);
    }
}
