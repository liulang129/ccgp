$(function () {
    $("#jqGrid").Grid({
        url: '../category/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, width: 40},
            {label: '课程名称', name: 'name', index: 'name', width: 80},
            {label: '封面图片', name: 'bannerUrl', index: 'bannerUrl', width: 80,formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '分类', name: 'typeName', index: 'typeName', width: 80},
            {label: '加入时间', name: 'createTime', index: 'create_time', width: 120,formatter: function (value) {
                return transDate(value);
            }}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        category: {isShow: 1, type: 0,  bannerUrl: '', iconUrl: '', imgUrl: '', wapBannerUrl: ''},
        ruleValidate: {
            name: [
                {required: true, message: '分类名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        typeList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.category = {isShow: 1, bannerUrl: '', iconUrl: '', imgUrl: '', wapBannerUrl: ''};
            this.getTpye();
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            this.getTpye();
            vm.getInfo(id)

        },
        getTpye: function () {
            Ajax.request({
                url: "../attributecategory/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.typeList = r.list;
                }
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.category.id == null ? "../category/save" : "../category/update";
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.category),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            let ids = getSelectedRows("#jqGrid");
            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../category/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../category/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.category = r.category;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            TreeGrid.table.refresh();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
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
        handleSuccessBannerUrl: function (res, file) {
            vm.category.bannerUrl = file.response.url;
        },
        eyeImageBannerUrl: function () {
            var url = vm.category.bannerUrl;
            eyeImage(url);
        },
        handleSuccessIconUrl: function (res, file) {
            vm.category.iconUrl = file.response.url;
        },
        eyeImageIconUrl: function () {
            var url = vm.category.iconUrl;
            eyeImage(url);
        },
        handleSuccessImgUrl: function (res, file) {
            vm.category.imgUrl = file.response.url;
        },
        eyeImageImgUrl: function () {
            var url = vm.category.imgUrl;
            eyeImage(url);
        },
        handleSuccessWapBannerUrl: function (res, file) {
            vm.category.wapBannerUrl = file.response.url;
        },
        eyeImageWapBannerUrl: function () {
            var url = vm.category.wapBannerUrl;
            eyeImage(url);
        }
    }
});