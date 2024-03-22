// pages/group/group.js
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    groupList:[],
    page: 1,
    size: 10,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1
  },
  onShareAppMessage: function () { 
    return {
      title: '团购更优惠', 
      desc: '团购更优惠',
      path: 'pages/group/group'
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getGroupList()
  },
  /**
       * 页面上拉触底事件的处理函数
       */
  onReachBottom: function () {
    this.getGroupList()
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    wx.showNavigationBarLoading();
    var self = this;
    self.setData({
      groupList: [],
      page: 1,
      totalPages: 1
    });
    self.getGroupList();
  },
  goDetail(e){
    console.log("====:", e.target.dataset.id)
    var id = e.target.dataset.id
    wx.navigateTo({
      url: '../goods/goods?id='+id+'&type=1'
    })
  },
  getGroupList() {
    let that = this;
    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return; 
    }
    util.request(api.GroupList, { page: that.data.page, size: that.data.size}).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          groupList: that.data.groupList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        });
        wx.hideLoading();
      }
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})