//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{},index:0},
	template:[
	          '<li :class="{active: (item.type===0 && index === 0)}">',
				  '<a v-if="item.type === 0" data-href="javascript:;">',
					  '<i v-if="item.icon != null" :class="item.icon"></i>',
					  '<span>{{item.name}}</span>',
					  '<i class="fa fa-angle-left pull-right"></i>',
				  '</a>',
				  '<ul v-if="item.type === 0" class="treeview-menu">',
					  '<menu-item :item="item" :index="index" v-for="(item, index) in item.list"></menu-item>',
				  '</ul>',
				  '<a v-if="item.type === 1" :data-href="item.url" onclick="menuABtn(this);">' +
					  '<i v-if="item.icon != null" :class="item.icon"></i>' +
					  '<i v-else class="fa fa-circle-o"></i><span>{{item.name}}</span>' +
				  '</a>',
	          '</li>'
	].join('')
});

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#kosmos_cloud',
	data:{
		user:{},
		menuList:{},
		main:"main.html",
		password:'',
		newPassword:'',
        navTitle:"欢迎页"
	},
	methods: {
		getMenuList: function () {
			$.getJSON(baseURL + "sys/menu/nav", function(r){
				vm.menuList = r.menuList;
                window.permissions = r.permissions;
			});
		},
		getUser: function(){
			$.getJSON(baseURL + "sys/user/info", function(r){
				vm.user = r.user;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: baseURL + "sys/user/password",
					    data: data,
					    dataType: "json",
					    success: function(r){
							if(r.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(){
									location.reload();
								});
							}else{
								layer.alert(r.msg);
							}
						}
					});
	            }
			});
		},
        logout: function () {
			//删除本地token
            localStorage.removeItem("token");
            //跳转到登录页面
            location.href = baseURL + 'login.html';
        }
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
    updated: function(){
        //路由
        var router = new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});
function menuABtn(obj){
	var $me=$(obj);
	if($me.attr("data-href")){
		if(!$("#pageTab").tabs('exists',$me.text())){
			$('#pageTab').tabs('add',{
				title:$me.text(),
				content: '<iframe scrolling="yes" frameborder="0" src="'+$me.attr("data-href")+'"></iframe>',
				closable: true
			});
		}else{
			$('#pageTab').tabs('select',$me.text());
		}
	}
};
function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
                $(".sidebar-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");

			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
function closeTab(menu, type) {
    var allTabs = $("#pageTab").tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function (i, n) {
        var opt = $(n).panel('options');
        if (opt.closable)
            allTabtitle.push(opt.title);
    });
    var curTabTitle = $(menu).data("tabTitle");
    var curTabIndex = $("#pageTab").tabs("getTabIndex", $("#pageTab").tabs("getTab", curTabTitle));
    switch (Number(type)){
        case 1://关闭当前
        	if(curTabIndex){
            $("#pageTab").tabs("close", curTabIndex);}
            break;
        case 2://全部关闭
            for (var i = 0; i < allTabtitle.length; i++) {
                $('#pageTab').tabs('close', allTabtitle[i]);
            }
            break;
        case 3://除此之外全部关闭
            for (var i = 0; i < allTabtitle.length; i++) {
                if (curTabTitle != allTabtitle[i])
                    $('#pageTab').tabs('close', allTabtitle[i]);
            }
            break;
        case 4://当前侧面右边
            for (var i = (curTabIndex); i < allTabtitle.length; i++) {
                $('#pageTab').tabs('close', allTabtitle[i]);
            }
            break;
        case 5: //当前侧面左边
            for (var i = 0; i < curTabIndex-1; i++) {
                $('#pageTab').tabs('close', allTabtitle[i]);
            }
            break;
        case 6: //刷新
            var $obj=$("#pageTab iframe").eq(curTabIndex);
            $obj.attr('src', $obj.attr('src'));
            break;
    }

}


$(document).ready(function () {
    //监听右键事件，创建右键菜单
    $('#pageTab').tabs({
        onContextMenu:function(e, title,index){
            e.preventDefault();
			$('#rightLayer').menu('show', {
				left: e.pageX,
				top: e.pageY
			}).data("tabTitle", title);
        }
    });
    //右键菜单click
    $("#rightLayer").menu({
        onClick : function (item) {
            closeTab(this, item.name);
        }
    });

 // 左侧菜单滑动
 $("#leftMenuBtn").click(function(){
 	var obj=$("#leftMenuBtn,.main-sidebar,.content-wrapper")
 	if($(this).hasClass("on")){
 		obj.removeClass("on");
 		$(this).animate({
 			"margin-left":"90px"
 		},300)
 		
 	}else{
 		obj.addClass("on");
 		$(this).animate({
 			"margin-left":"0"
 		},300)
 	}
 });
});
