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

import com.platform.entity.GbCoachLevelEntity;
import com.platform.service.GbCoachLevelService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 教练级别Controller
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 10:57:45
 */
@RestController
@RequestMapping("gbcoachlevel")
public class GbCoachLevelController {
    @Autowired
    private GbCoachLevelService gbCoachLevelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gbcoachlevel:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GbCoachLevelEntity> gbCoachLevelList = gbCoachLevelService.queryList(query);
        int total = gbCoachLevelService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gbCoachLevelList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gbcoachlevel:info")
    public R info(@PathVariable("id") Integer id) {
        GbCoachLevelEntity gbCoachLevel = gbCoachLevelService.queryObject(id);

        return R.ok().put("gbCoachLevel", gbCoachLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("gbcoachlevel:save")
    public R save(@RequestBody GbCoachLevelEntity gbCoachLevel) {
        gbCoachLevelService.save(gbCoachLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("gbcoachlevel:update")
    public R update(@RequestBody GbCoachLevelEntity gbCoachLevel) {
        gbCoachLevelService.update(gbCoachLevel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("gbcoachlevel:delete")
    public R delete(@RequestBody Integer[] ids) {
        gbCoachLevelService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GbCoachLevelEntity> list = gbCoachLevelService.queryList(params);

        return R.ok().put("list", list);
    }
}
