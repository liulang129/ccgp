package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 教练级别实体
 * 表名 gb_coach_level
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 10:57:45
 */
public class GbCoachLevelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //级别名称
    private String name;
    //分佣比例
    private Double fx;
    //创建时间
    private Date creatTime;
    //修改时间
    private Date modifyTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：级别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：级别名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：分佣比例
     */
    public void setFx(Double fx) {
        this.fx = fx;
    }

    /**
     * 获取：分佣比例
     */
    public Double getFx() {
        return fx;
    }
    /**
     * 设置：创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }
    /**
     * 设置：修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }
}
