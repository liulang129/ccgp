package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 教练信息实体
 * 表名 gb_coach
 *
 * @author liukq
 * @email 906260146@qq.com
 * @date 2023-12-22 15:35:47
 */
public class GbCoachEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //所在省
    private String provinceName;
    //所在市
    private String cityName;
    //所在区
    private String countyName;
    //手机号
    private String mobile;
    //姓名
    private String name;
    //昵称
    private String nickName;
    //密码
    private String password;
    //头像
    private String avatar;
    //级别
    private String level;
    //级别id
    private Integer levelId;
    //余额
    private BigDecimal balance;
    //微信号
    private String weixin;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    //支付宝
    private String alipay;
    //银行卡
    private String bankCard;
    //身份证号
    private String idCard;
    //常驻地址
    private String address;
    //身份证正面
    private String idCardFront;
    //身份证反面
    private String idCardBack;
    //账户状态：0，锁定；1，正常
    private Integer status;
    //认证状态:0,待审核；1，未认证；2，已认证
    private Integer identification;
    //市场负责人
    private String pic;
    //修改时间
    public  Date getModifyTime() {
        return modifyTime;
    }public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }private Date modifyTime;
    //创建时间
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
     * 设置：所在省
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取：所在省
     */
    public String getProvinceName() {
        return provinceName;
    }
    /**
     * 设置：所在市
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取：所在市
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * 设置：所在区
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * 获取：所在区
     */
    public String getCountyName() {
        return countyName;
    }
    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取：昵称
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：头像
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置：级别
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取：级别
     */
    public String getLevel() {
        return level;
    }
    /**
     * 设置：余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取：余额
     */
    public BigDecimal getBalance() {
        return balance;
    }
    /**
     * 设置：微信号
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * 获取：微信号
     */
    public String getWeixin() {
        return weixin;
    }
    /**
     * 设置：支付宝
     */
    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    /**
     * 获取：支付宝
     */
    public String getAlipay() {
        return alipay;
    }
    /**
     * 设置：银行卡
     */
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    /**
     * 获取：银行卡
     */
    public String getBankCard() {
        return bankCard;
    }
    /**
     * 设置：身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取：身份证号
     */
    public String getIdCard() {
        return idCard;
    }
    /**
     * 设置：常驻地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：常驻地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：身份证正面
     */
    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    /**
     * 获取：身份证正面
     */
    public String getIdCardFront() {
        return idCardFront;
    }
    /**
     * 设置：身份证反面
     */
    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    /**
     * 获取：身份证反面
     */
    public String getIdCardBack() {
        return idCardBack;
    }
    /**
     * 设置：账户状态：0，锁定；1，正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：账户状态：0，锁定；1，正常
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：认证状态:0,待审核；1，未认证；2，已认证
     */
    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    /**
     * 获取：认证状态:0,待审核；1，未认证；2，已认证
     */
    public Integer getIdentification() {
        return identification;
    }
    /**
     * 设置：市场负责人
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取：市场负责人
     */
    public String getPic() {
        return pic;
    }
}
