var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var app = getApp();
Page({
  data: {
    gbCoach: {
      id:0,
      password: '',
      confirmPassword: '',
      captcha: '',
      pic: '',
      mobile: ''
    },
    isDisabled:false,
    getCodeButtonText: '获取验证码',
    userId:0,
    userInfo:{}
  },
  bindinputMobile(event) {
    let gbCoach = this.data.gbCoach;
      gbCoach.mobile = event.detail.value;
    this.setData({
        gbCoach: gbCoach
    });
  },
    bindinputCaptcha(event) {
    let gbCoach = this.data.gbCoach;
      gbCoach.captcha = event.detail.value;
    this.setData({
        gbCoach: gbCoach
    });
  },
    bindPasswordInput (event){
    let gbCoach = this.data.gbCoach;
      gbCoach.password = event.detail.value;
    this.setData({
        gbCoach: gbCoach
    });
  },
    bindConfirmPasswordInput (event){
        let gbCoach = this.data.gbCoach;
        gbCoach.confirmPassword = event.detail.value;
        this.setData({
            gbCoach: gbCoach
        });
    },
    bindinputPic (event){
        let gbCoach = this.data.gbCoach;
        gbCoach.pic = event.detail.value;
        this.setData({
            gbCoach: gbCoach
        });
    },
  savegbCoach(){
    let gbCoach = this.data.gbCoach;

    if (gbCoach.mobile == '') {
      util.showErrorToast('请输入手机号码');
      return false;
    } 
    
    if (!util.validatePhone(gbCoach.mobile)) {
      util.showErrorToast('请输入正确手机号码');
      return false;
    }

      if (gbCoach.captcha == '') {
          util.showErrorToast('请输入验证码');
          return false;
      }
      if (gbCoach.password == '') {
          util.showErrorToast('请输入密码');
          return false;
      }
      if (gbCoach.confirmPassword == '') {
          util.showErrorToast('请输入确认密码');
          return false;
      }
      if (gbCoach.password != gbCoach.confirmPassword) {
          util.showErrorToast('密码不一致');
          return false;
      }
    if (gbCoach.pic == '') {
        util.showErrorToast('请输入市场负责人ID');

        return false;
    }
    let that = this;
    that.setData({
      isDisabled: true
    });
      wx.request({
          url: api.gbCoachSave,
          data: {
              id: gbCoach.id,
              captcha: gbCoach.captcha,
              password: gbCoach.password,
              mobile: gbCoach.mobile,
              pic: gbCoach.pic,
          },
          method: 'POST',
          header: {
              'content-type': 'application/json'
          },
          success: function (res) {
              console.log('-----********---------',res)
              if (res.statusCode == 200) {
                  console.log('-----********code---------',res)
                  that.setData({
                      'loginErrorCount': 0
                  });
                  wx.setStorageSync("token",res.data.token);
                  console.log('-----********22222---------')
                  wx.navigateBack({
                      url: '/pages/index/index'
                  });
              }
          }
      });
  },
  onShow: function () {
    // 页面显示

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },
    countDownPassCode: function () {
        util.request(api.SmsCode, { mobile: this.data.gbCoach.mobile}, 'POST')
            .then(function (res) {
                if (res.data.code == 200) {
                    wx.showToast({
                        title: '发送成功',
                        icon: 'success',
                        duration: 1000
                    })
                    var pages = getCurrentPages()
                    var i = 60;
                    var intervalId = setInterval(function () {
                        i--
                        if (i <= 0) {
                            pages[pages.length - 1].setData({
                                disableGetMobileCode: false,
                                disableSubmitMobileCode: false,
                                getCodeButtonText: '获取验证码'
                            })
                            clearInterval(intervalId)
                        } else {
                            pages[pages.length - 1].setData({
                                getCodeButtonText: i,
                                disableGetMobileCode: true,
                                disableSubmitMobileCode: false
                            })
                        }
                    }, 1000);
                }
            });
    }
})