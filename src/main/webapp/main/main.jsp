<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/form.validator.rules.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
    var login = "${sessionScope.admin}";
    if(login == ""){
        location.href="${pageContext.request.contextPath}/login.jsp";
    }
    $(function () {
        //页面加载完成之后显示菜单数据

        $.post("${pageContext.request.contextPath}/Menu/queryAllMenu",function (menu) {
            //通过accordion的添加方法追加菜单
            console.log(menu);
            //遍历一级菜单
            $.each(menu,function (index,m) {
                //遍历二级菜单
                var content = "<div style='text-align: center;'>";
                $.each(m.children,function(idx,child){
                    content += "<a onclick=\"addTabs('"+child.title+"','"+child.iconCls+"','"+child.href+"')\" style='width:95%;margin:10px 0px; border: 2px #E0ECFF;' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+child.iconCls+"'\">"+child.title+"</a><br>";
                });
                content += "</div>"
                //添加菜单
                $("#menu").accordion('add',{
                    title:m.title,
                    iconCls:m.iconCls,
                    content:content,
                })
            });

        });
    });

    //点击菜单追加选项卡
    function addTabs(title,iconCls,href){
        //添加以前先判断tabs中是否存在这个选项卡
        if(!$("#tabs").tabs('exists',title)){
            $("#tabs").tabs('add',{
                title:title,
                iconCls:iconCls,
                closable:true,
                fit:true,
                href:"${pageContext.request.contextPath}/"+href,
            });
        }else{
            $("#tabs").tabs('select',title);
        }

    }


    function LoggedOut() {
        location.href="${pageContext.request.contextPath}/Admin/LoggedOut"
    }
    function updatePwd(id){
        $("#editAdminUpdate").dialog({
            href:'${pageContext.request.contextPath}/main/Admin/updatePwd.jsp?id=${sessionScope.admin.id}&Password=${sessionScope.admin.password}',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    var  aPassword='${sessionScope.admin.password}';
                    var  password=$("#Pwd1").val();
                    var  password1=$("#Pwd2").val();
                    console.log(password1);
                    var  password2=$("#Pwd3").val();
                    console.log(password2);
                    if(aPassword!=password){
                        $.messager.show({title:'提示',msg:'和原密码不一致'});

                    }else if(password1!=password2){
                        $.messager.show({title:'提示',msg:'两次密码不一致'});
                    }else{
                        //保存用户信息
                        $("#editAdminUpdatePwd").form('submit',{
                            url:'${pageContext.request.contextPath}/Admin/updatePwd',
                            success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    //提示信息
                                    $.messager.show({title:'提示',msg:"用户修改成功!!!"});
                                    window.location.reload();
                                }else{
                                    //提示信息
                                    $.messager.show({title:'提示',msg:resultObj.message});
                                }
                                //关闭对话框
                                $("#editAdminUpdate").dialog('close');
                                //刷新datagrid
                                $("#deptDg").datagrid('reload');
                            }
                        });
                    }
                },
            },

                {
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#editAdminUpdate").dialog('close');
                }

            },]
        });
    }

</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>

    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.admin.name} &nbsp;<a href="#" onclick="updatePwd()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#" onclick="LoggedOut()" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
        <div id="editAdminUpdate" data-options="width:600,height:400,iconCls:'icon-add',title:'修改密码'"></div>
    </div>

    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
        <div id="menu" class="easyui-accordion" data-options="fit:true"></div>
    </div>   
    <div data-options="region:'center'">
    	<div id="tabs" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>