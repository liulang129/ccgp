$(function () {
    $("#jqGrid").Grid({
        url: '../brand/list',
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, with: 40
        }, {
            label: '小区名称', name: 'name', index: 'name', width: 80
        },  {
            label: '小区地址', name: 'simpleDesc', index: 'simple_desc', width: 80
        },  {
            label: '小区图片', name: 'appListPicUrl', index: 'app_list_pic_url', width: 80, formatter: function (value) {
                return transImg(value);
            }
        }, {
            label: '添加时间', name: 'cTime', index: 'c_time', width: 80
        }, {
            label: '修改时间', name: 'uTime', index: 'u_time', width: 80
        }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        brand: {listPicUrl: '', picUrl: '', appListPicUrl: '', newPicUrl: '', isShow: 1, isNew: 0, newSortOrder:1, sortOrder:1, floorPrice:''},
        ruleValidate: {
            name: [
                {required: true, message: '小区名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        school: {
            gender: 1,
            addressEntity:{
                proId:0,
                provinceName:'',
                cityName:'',
                countyName:''
            }
        },
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
            vm.brand = {
                listPicUrl: '',
                picUrl: '',
                appListPicUrl: '',
                newPicUrl: '',
                isShow: 1,
                isNew: 0,
                newSortOrder: 1,
                sortOrder: 1,
                floorPrice: ''
            };
            this.getProvinceNames();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.brand.id == null ? "../brand/save" : "../brand/update";
            console.log(JSON.stringify(vm.brand));
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.brand),
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {

                Ajax.request({
                    type: "POST",
                    url: "../brand/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../brand/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.brand = r.brand;
                }
            });
            // vm.selProId = vm.school.addressEntity.provinceName;
            // vm.selCityProId = vm.school.addressEntity.cityName;
            // console.log('vm.selProId-------',vm.selProId);
            // console.log('vm.selCityProId-------',vm.selCityProId);
            // console.log('addressEntity-------',vm.school.addressEntity);
            this.getProvinceNames();
            // this.getCityNames();
            // this.getCountyNames();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.brand.listPicUrl = file.response.url;
        },
        handleSuccessPicUrl: function (res, file) {
            vm.brand.picUrl = file.response.url;
        },
        handleSuccessAppListPicUrl: function (res, file) {
            vm.brand.appListPicUrl = file.response.url;
        },
        handleSuccessNewPicUrl: function (res, file) {
            vm.brand.newPicUrl = file.response.url;
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
        eyeImageListPicUrl: function () {
            var url = vm.brand.listPicUrl;
            eyeImage(url);
        },
        eyeImagePicUrl: function () {
            var url = vm.brand.picUrl;
            eyeImage(url);
        },
        eyeImageAppListPicUrl: function () {
            var url = vm.brand.appListPicUrl;
            eyeImage(url);
        },
        eyeImageNewPicUrl: function () {
            var url = vm.brand.newPicUrl;
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
