package com.platform.api;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.GbCoachVo;
import com.platform.service.ApiGbCoachService;

/**
 * 教练信息Controller
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
@Api(tags = "教练信息")
@RestController
@RequestMapping("/api/gbcoach")
public class ApiGbCoachController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ApiGbCoachService apiGbCoachService;

    @Autowired
    private ApiUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 保存
     */
    @ApiOperation(value = "教练入驻",  response = Map.class)
    @IgnoreAuth
    @PostMapping("/save")
    public R save(@LoginUser UserVo loginUser) {
        JSONObject gbcoachJson = this.getJsonRequest();
        GbCoachVo entity = new GbCoachVo();
        String mobile = "";
        String captcha="";
        String password ="";
        if (null != gbcoachJson) {
            mobile = gbcoachJson.getString("mobile");
            captcha = gbcoachJson.getString("captcha");
            password = gbcoachJson.getString("password");
            if (StringUtils.isEmpty(mobile)) {
                toResponsFail("用户手机号不可为空");
            }
            if (!apiGbCoachService.verificationCode(mobile, captcha)) {
                toResponsFail("验证码证错误");
            }
            ;
            entity.setId(gbcoachJson.getLong("id"));
            //entity.setUserId(loginUser.getUserId());
            entity.setMobile(gbcoachJson.getString("mobile"));
            entity.setPassword(DigestUtils.sha256Hex(password));
            entity.setPic(gbcoachJson.getLong("pic"));
            userService.save(mobile, password);
        }
        UserVo userVo = userService.queryByMobile(mobile);
        if(null != userVo.getUserId()) {
            entity.setUserId(userVo.getUserId());
        }
        apiGbCoachService.save(entity);

        //生成token
        Map<String, Object> map = tokenService.createToken(userVo.getUserId());

        return R.ok(map);
    }

    @ApiOperation(value="发送验证码" ,httpMethod="POST")
    @PostMapping("/captcha")
    public Object captcha(@ApiParam(name = "mobile", value = "手机号") @RequestParam String mobile) {
        apiGbCoachService.sendCode(mobile);
        return toResponsSuccess("发送成功");
    }


}
