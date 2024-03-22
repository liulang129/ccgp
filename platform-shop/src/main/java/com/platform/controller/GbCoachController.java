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

import com.platform.entity.GbCoachEntity;
import com.platform.service.GbCoachService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 教练信息Controller
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
@RestController
@RequestMapping("gbcoach")
public class GbCoachController {
    @Autowired
    private GbCoachService gbCoachService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gbcoach:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GbCoachEntity> gbCoachList = gbCoachService.queryList(query);
        int total = gbCoachService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gbCoachList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gbcoach:info")
    public R info(@PathVariable("id") Integer id) {
        GbCoachEntity gbCoach = gbCoachService.queryObject(id);

        return R.ok().put("gbCoach", gbCoach);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("gbcoach:save")
    public R save(@RequestBody GbCoachEntity gbCoach) {
        gbCoachService.save(gbCoach);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("gbcoach:update")
    public R update(@RequestBody GbCoachEntity gbCoach) {
        gbCoachService.update(gbCoach);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("gbcoach:delete")
    public R delete(@RequestBody Integer[] ids) {
        gbCoachService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GbCoachEntity> list = gbCoachService.queryList(params);

        return R.ok().put("list", list);
    }
}
