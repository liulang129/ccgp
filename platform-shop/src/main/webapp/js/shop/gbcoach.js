	$(function () {
    $("#jqGrid").Grid({
        url: '../gbcoach/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, width: 40},
            {label: '姓名', name: 'name', index: 'name', width: 80},
			{label: '手机号', name: 'mobile', index: 'mobile', width: 80},
			{label: '昵称', name: 'nickName', index: 'nick_name', width: 80},
			{label: '头像', name: 'avatar', index: 'avatar', width: 80, formatter: function (value) {
                return transImg(value);
           	 }
            },
			{label: '级别', name: 'level', index: 'level', width: 60},
			{label: '余额', name: 'balance', index: 'balance', width: 60},
            {label: '市场负责人', name: 'pic', index: 'pic', width: 120},
            {
                label: '账户状态', name: 'status', width: 60, formatter: function (value) {
                if (value == 0 ) {
                    return '<span class="label label-danger">锁定</span>'
                }else if(value == 1){
                    return '<span class="label label-success">正常</span>'
                }
           	 }
            },
            {
                label: '认证状态', name: 'identification', width: 60, formatter: function (value) {
                if (value == 0 ) {
                    return '<span class="label label-danger">待审核</span>'
                }else if(value == 1){
                    return '<span class="label label-success">未认证</span>'

                }else{
                    return '<span class="label label-danger">已认证</span>'
                }
           	 }
            },
			{label: '加入时间', name: 'createTime', index: 'create_time', width: 120,formatter: function (value) {
                return transDate(value);
            }}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		gbCoach: {
            levelId: '1'
        },
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        coachLevels: [],
        provinceNames:[],
        selProId:0,
        selCityProId:0,
        cityNames:[],
        selCityId:0,
        countyNames:[],
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.gbCoach = {
                levelId: '1'
            };
            vm.coachLevels = [];

            this.getCoachLevels();
            this.getProvinceNames();
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            this.getCoachLevels();
            this.getProvinceNames();
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.gbCoach.id == null ? "../gbcoach/save" : "../gbcoach/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.gbCoach),
                type: "POST",
			    contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../gbcoach/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
				    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
					}
				});
			});
		},
		getInfo: function(id){
            Ajax.request({
                url: "../gbcoach/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.gbCoach = r.gbCoach;
                }
            });
            this.getProvinceNames();
		},
        /**
         * 获取教练级别
         */
        getCoachLevels: function () {
            Ajax.request({
                url: "../gbcoachlevel/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.coachLevels = r.list;
                }
            });
        },
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleSuccessIdCardFrontUrl: function (res, file) {
            vm.gbCoach.idCardFront = file.response.url;
        },
        handleSuccessIdCardBackUrl: function (res, file) {
            vm.gbCoach.idCardBack= file.response.url;
        },
        handleSuccessAvatarUrl: function (res, file) {
            vm.gbCoach.avatar= file.response.url;
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        eyeIdCardFrontUrl: function () {
            var url = vm.gbCoach.idCardFront;
            eyeImage(url);
        },
        eyeIdCardBackUrl: function () {
            var url = vm.gbCoach.idCardBack;
            eyeImage(url);
        },
        eyeAvatarUrl: function () {
            var url = vm.gbCoach.avatar;
            eyeImage(url);
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        /**
         * 获取省列表
         */
        getProvinceNames: function () {
            Ajax.request({
                url: "../sys/region/getAllProvice?areaId=" + 1,
                async: true,
                successCallback: function (r) {
                    vm.provinceNames = r.list;
                }
            });
        },

        /**
         * 选择省操作调用方法
         */
        proNameChange: function (val) {
            console.log('-------val', val);
            if (val == null || val == "") {
                console.log('proNameChange-------val', val);
                return;
            }
            vm.selProId = val;
            vm.getCityNames();
        },
        /**
         * 获取市列表
         */
        getCityNames: function () {
            if (vm.selProId == null || vm.selProId == "") {
                console.log('getCityNames-------vm.selProId', vm.selProId);
                return;
            }
            Ajax.request({
                url: "../sys/region/getAllCityByName?areaName=" + vm.selProId,
                async: true,
                successCallback: function (r) {
                    vm.cityNames = r.list;
                }
            });
        },
        /**
         * 选择市操作调用方法
         */
        proNameCityChange: function (val) {
            console.log('-------val2', val);
            if (val == null || val == "") {
                console.log('proNameCityChange-------val', val);
                return;
            }
            vm.selCityProId = val;
            vm.getCountyNames();
        },
        /**
         * 获取区列表
         */
        getCountyNames: function () {
            if (vm.selCityProId == null || vm.selCityProId == "") {
                console.log('getCountyNames-------vm.selCityProId', vm.selCityProId);
                return;
            }
            Ajax.request({
                url: "../sys/region/getChildrenDistrictByName?areaName=" + vm.selCityProId,
                contentType:"application/x-www-form-urlencoded;charset=utf-8",
                async: true,
                successCallback: function (r) {
                    vm.countyNames = r.list;
                }
            });
        }
	}
});