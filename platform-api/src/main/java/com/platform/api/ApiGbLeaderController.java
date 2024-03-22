package com.platform.api;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.*;
import com.platform.service.*;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 团长审核表Controller
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-09 23:45:08
 */
@Api(tags = "团长申请")
@RestController
@RequestMapping("/api/gbleader")
public class ApiGbLeaderController extends ApiBaseAction {
    @Autowired
    private ApiGbLeaderService gbLeaderService;

    /**
     * 保存
     */
    @ApiOperation(value = "团长申请",  response = Map.class)
    @PostMapping("save")
    public Object save(@LoginUser UserVo loginUser) {
        JSONObject gbleaderJson = this.getJsonRequest();
        GbLeaderVo entity = new GbLeaderVo();
        if (null != gbleaderJson) {
            entity.setId(gbleaderJson.getLong("id"));
            entity.setUserId(loginUser.getUserId());
            entity.setRealname(gbleaderJson.getString("realname"));
            entity.setMobile(gbleaderJson.getString("mobile"));
            entity.setPic(gbleaderJson.getLong("pic"));

        }
        if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            gbLeaderService.save(entity);
        }
        return toResponsSuccess(entity);
    }



}
