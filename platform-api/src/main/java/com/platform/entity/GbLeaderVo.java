package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 团长审核表实体
 * 表名 gb_leader
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-09 23:45:08
 */
public class GbLeaderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //用户Id
    private Long userId;
    //真实姓名
    private String realname;
    //
    private String mobile;
    //负责人ID
    private Long pic;
    //注册时间
    private Date registerTime;
    //修改时间
    private Date modifyTime;
    //状态 0待审核;1已审核;2已拒绝;
    private Integer status;
    //备注
    private String remarks;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPic() {
        return pic;
    }

    public void setPic(Long pic) {
        this.pic = pic;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取：真实姓名
     */
    public String getRealname() {
        return realname;
    }
    /**
     * 设置：
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取：注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
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
    /**
     * 设置：状态 0待审核;1已审核;2已拒绝;
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 0待审核;1已审核;2已拒绝;
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }
}
