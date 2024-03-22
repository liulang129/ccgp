package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.GbLeaderEntity;
import com.platform.service.GbLeaderService;
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
@RestController
@RequestMapping("gbleader")
public class GbLeaderController {
    @Autowired
    private GbLeaderService gbLeaderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gbleader:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GbLeaderEntity> gbLeaderList = gbLeaderService.queryList(query);
        int total = gbLeaderService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gbLeaderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gbleader:info")
    public R info(@PathVariable("id") Integer id) {
        GbLeaderEntity gbLeader = gbLeaderService.queryObject(id);

        return R.ok().put("gbLeader", gbLeader);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("gbleader:save")
    public R save(@RequestBody GbLeaderEntity gbLeader) {
        gbLeaderService.save(gbLeader);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("gbleader:update")
    public R update(@RequestBody GbLeaderEntity gbLeader) {
        gbLeaderService.update(gbLeader);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("gbleader:delete")
    public R delete(@RequestBody Integer[] ids) {
        gbLeaderService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GbLeaderEntity> list = gbLeaderService.queryList(params);

        return R.ok().put("list", list);
    }
}
