<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#UserDg").datagrid({
           url:"${pageContext.request.contextPath}/User/findAll",
            pagination:true,
            toolbar:'#usertb',
            resizable:true,
           columns:[[
               {title:"id",field:'id',width:100,},
               {title:"手机号",field:'phoneNum',width:100,},
               {title:"用户名称",field:'username',width:100,},
               {title:"用户密码",field:'password',width:100,},
               {title:"用户省份",field:'province',width:100,},
               {title:"用户城市",field:'city',width:100,},
               {title:"用户法名",field:'nickName',width:100,},
               {title:"用户性别",field:'gender',width:100,},
               {title:"用户签名",field:'sign',width:100,},
               {title:"用户状态",field:'status',width:100,},
               {title:'options',field:'options',width:200,
                   formatter:function(value,row,index){
                       return "<a href='javascript:;' class='options' onclick=\"openEditUserUpdate('"+row.id+"')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                   }
               }
           ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/user/' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.userdate + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },


            onLoadSuccess:function () {
            $(".options").linkbutton();
        },
            toolbar:'#usertb',
        });
        });


    function openUserDialog(){
        $("#saveUser").dialog({
            href:'${pageContext.request.contextPath}/main/User/save.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#saveUserInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/User/save',
                        success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示信息
                                $.messager.show({title:'提示',msg:"用户添加成功!!!"});
                            }else{
                                //提示信息
                                $.messager.show({title:'提示',msg:resultObj.message});
                            }
                            //关闭对话框
                            $("#saveUser").dialog('close');
                            //刷新datagrid
                            $("#UserDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#saveUser").dialog('close');
                }
            }]
        });
    }

    function openEditUserUpdate(id){
        $("#editUserUpdate").dialog({
            href:'${pageContext.request.contextPath}/main/User/edit.jsp?id='+id,
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#editUserInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/User/update',
                        success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示信息
                                $.messager.show({title:'提示',msg:"用户修改成功!!!"});
                            }else{
                                //提示信息
                                $.messager.show({title:'提示',msg:resultObj.message});
                            }
                            //关闭对话框
                            $("#editUserUpdate").dialog('close');
                            //刷新datagrid
                            $("#UserDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#editUserUpdate").dialog('close');
                }
            }]
        });
    }
</script>
<table id="UserDg" class="easyui-datagrid" data-options="fit:true">
</table>
<%--保存用户对话框--%>
<div id="saveUser" data-options="width:600,height:400,iconCls:'icon-add',title:'添加用户'"></div>
<div id="editUserUpdate" data-options="width:600,height:400,iconCls:'icon-add',title:'修改用户'"></div>
<div id="usertb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="openUserDialog()">添加</a>
</div>

