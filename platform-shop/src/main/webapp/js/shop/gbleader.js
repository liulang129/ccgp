$(function () {
    $("#jqGrid").Grid({
        url: '../gbleader/list',
        colModel: [
			{label: 'Id', name: 'user_id', index: 'id', key: true, hidden: true},
			{label: '用户名', name: 'username', index: 'username', width: 80},
			{label: '真实姓名', name: 'realname', index: 'realname', width: 80},
			{label: '手机号码', name: 'mobile', index: 'mobile', width: 80},
			{label: '负责人', name: 'pic', index: 'pic', width: 80},
			{label: '注册时间', name: 'registerTime', index: 'register_time', width: 80 ,formatter: function (value) {
                return transDate(value);
            }},
			{label: '修改时间', name: 'modifyTime', index: 'modify_time', width: 80,formatter: function (value) {
                return transDate(value);
            }},
            {
                label: '状态', name: 'status', width: 80, formatter: function (value) {
					if (value == 0 ) {
						return '<span class="label label-danger">待审核</span>'
					}else if(value == 1){
					   return '<span class="label label-success">已审核</span>'
					}else{
                        return '<span class="label label-danger">已拒绝</span>'
					}
				}
            }
		]
    });
});

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		gbLeader: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.gbLeader = {};
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
            var url = vm.gbLeader.id == null ? "../gbleader/save" : "../gbleader/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.gbLeader),
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
            var ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../gbleader/delete",
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
                url: "../gbleader/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.gbLeader = r.gbLeader;
                }
            });
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
        }
	}
});