var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data:{
    orderId:'',
    orderList: [],
    page: 1,
    size: 10,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1
  },
  onLoad:function(options){ 
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      orderId: options.id
    }); 
    wx.showLoading({
      title: '加载中...',
      success: function () {

      }
    });
    this.getOrderList();
  },

  switchCate: function (event) { 
    wx.showLoading({
      title: '加载中...'
    });
    var currentTarget = event.currentTarget;  
    this.setData({
      orderId: currentTarget.dataset.id,
      totalPages:1,
      page:1,
      orderList:[]
    });
    console.log(this.data.orderId)
    this.getOrderList();
  }, 

  /**
       * 页面上拉触底事件的处理函数
       */
  onReachBottom: function () { 
    this.getOrderList()
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    wx.showNavigationBarLoading();
    var self = this;
    self.setData({
      orderList:[],
      page: 1,
      totalPages: 1
    });
    self.getOrderList();
  },

  getOrderList(){
    let that = this;

    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }
    that.data.orderId = that.data.orderId == -1 ? "" : that.data.orderId;
    util.request(api.OrderList, { page: that.data.page, size: that.data.size, order_status: that.data.orderId}).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          orderList: that.data.orderList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        });
        wx.hideLoading();
      }
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    });
  },
  payOrder(event){
      let that = this;
      let orderIndex = event.currentTarget.dataset.orderIndex;
      let order = that.data.orderList[orderIndex];
      wx.redirectTo({
          url: '/pages/pay/pay?orderId=' + order.id + '&actualPrice=' + order.actual_price,
      })
  },
  cancelOrder(event) {
    console.log('开始取消订单');
    let that = this; 
    let orderIndex = event.currentTarget.dataset.orderIndex;
    let order = that.data.orderList[orderIndex];
    console.log('可以取消订单的情况');
    wx.showModal({
      title: '',
      content: '确定要取消此订单？',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定');

          util.request(api.OrderCancel, {
            orderId: order.id
          }).then(function (res) {
            console.log(res.errno);
            if (res.errno === 0) {
              console.log(res.data);
              wx.showModal({
                title: '提示',
                content: res.data,
                showCancel: false,
                confirmText: '继续',
                success: function (res) {
                  //  util.redirect('/pages/ucenter/order/order');
                  wx.navigateBack({
                    url: 'pages/ucenter/order/order',
                  });
                }
              });
            }
          });

        }
      }
    });
  },
  confirmOrder(event) {
    //确认收货
    console.log('开始确认收货');
    let that = this;
    let orderIndex = event.currentTarget.dataset.orderIndex;
    let order = that.data.orderList[orderIndex];
    console.log('可以取消订单的情况');
    wx.showModal({
      title: '',
      content: '确定已经收到商品？',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定');

          util.request(api.OrderConfirm, {
            orderId: order.id
          }).then(function (res) {
            console.log(res.errno);
            if (res.errno === 0) {
              console.log(res.data);
              wx.showModal({
                title: '提示',
                content: res.data,
                showCancel: false,
                confirmText: '继续',
                success: function (res) {
                  //  util.redirect('/pages/ucenter/order/order');
                  wx.navigateBack({
                    url: 'pages/ucenter/order/order',
                  });
                }
              });
            }
          });

        }
      }
    });
  },
  buyOrder(event){
    let that = this;
    let orderIndex = event.currentTarget.dataset.orderIndex;
    let order = that.data.orderList[orderIndex];
    console.log("------------------")
    console.log(order.goods_id)
    wx.navigateTo({
      url: '/pages/goods/goods?id=' + order.goods_id,
    });
  },
  commentOrder(event) {
    let that = this;
    let orderIndex = event.currentTarget.dataset.orderIndex;
    let order = that.data.orderList[orderIndex]; 
    wx.navigateTo({
      url: '/pages/commentPost/commentPost?typeId=0&valueId=' + order.goods_id,
    });
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){

  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  }
})