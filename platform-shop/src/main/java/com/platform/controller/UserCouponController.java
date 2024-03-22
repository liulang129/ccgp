package com.platform.controller;

import com.platform.entity.SysUserEntity;
import com.platform.entity.UserCouponEntity;
import com.platform.service.UserCouponService;
import com.platform.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2017-08-19 15:40:33
 */
@RestController
@RequestMapping("usercoupon")
public class UserCouponController {
    @Autowired
    private UserCouponService userCouponService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("usercoupon:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        Query query = new Query(params);
        query.put("merchantId",sysUserEntity.getMerchantId());
        List<UserCouponEntity> userCouponList = userCouponService.queryList(query);
        int total = userCouponService.queryTotal(query);
        for(UserCouponEntity user : userCouponList) {
        	user.setUserName(Base64.decode(user.getUserName()));
        }
        PageUtils pageUtil = new PageUtils(userCouponList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("usercoupon:info")
    public R info(@PathVariable("id") Integer id) {
        UserCouponEntity userCoupon = userCouponService.queryObject(id);

        return R.ok().put("userCoupon", userCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usercoupon:save")
    public R save(@RequestBody UserCouponEntity userCoupon) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        userCoupon.setMerchantId(sysUserEntity.getMerchantId());
        userCouponService.save(userCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usercoupon:update")
    public R update(@RequestBody UserCouponEntity userCoupon) {
        userCouponService.update(userCoupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("usercoupon:delete")
    public R delete(@RequestBody Integer[] ids) {
        userCouponService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UserCouponEntity> list = userCouponService.queryList(params);

        return R.ok().put("list", list);
    }
}
