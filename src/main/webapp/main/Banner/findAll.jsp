<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#deptDg").datagrid({
           url:"${pageContext.request.contextPath}/Banner/findAll",

            pagination:true,

           toolbar:'#tb',
           columns:[[
               {title:"id",field:'id',width:120,},
               {title:"标题",field:'title',width:120,},
               {title:"图片路径",field:'imgPath',width:120,},
               {title:"描述",field:'description',width:120,},
               {title:"状态",field:'status',width:120,},
               {title:"日期",field:'date',width:120,
               },
               {title:'options',field:'options',width:200,
                   formatter:function(value,row,index){
                       return "<a href='javascript:;' class='options' onclick=\"delRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;" +
                           "<a href='javascript:;' class='options' onclick=\"openEditBannerUpdate('"+row.id+"')\" data-options=\"iconCls:'icon-edit',plain:true\">更新</a>";
                   }
               }
           ]],

            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/banner/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.date + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },

        onLoadSuccess:function () {
            $(".options").linkbutton();
        },
        toolbar:'#tb',
        });
        });



    //删除一行的事件
    function delRow(id){
        //获取当前点击id发送ajax请求删除id这个人的信息
        $.post("${pageContext.request.contextPath}/Banner/delete",{"id":id},function (result) {//响应成功之后回调
            //刷新datagrid数据
            $("#deptDg").datagrid('reload');//刷新当前datagrid
        });
    }

    function openDeptDialog(){
        $("#saveBannertDialog").dialog({
            href:'${pageContext.request.contextPath}/main/Banner/save.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#saveBannerInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Banner/save',
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
                            $("#saveBannertDialog").dialog('close');
                            //刷新datagrid
                            $("#deptDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#saveBannertDialog").dialog('close');
                }
            }]
        });
    }

    function openEditBannerUpdate(id){
        $("#editBannerUpdate").dialog({
            href:'${pageContext.request.contextPath}/main/Banner/edit.jsp?id='+id,
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#editBannerInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Banner/update',
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
                            $("#editBannerUpdate").dialog('close');
                            //刷新datagrid
                            $("#deptDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#editBannerUpdate").dialog('close');
                }
            }]
        });
    }
</script>
<table id="deptDg" class="easyui-datagrid" data-options="fit:true">


</table>
<%--保存用户对话框--%>
<div id="saveBannertDialog" data-options="width:600,height:400,iconCls:'icon-add',title:'添加图片'"></div>

<div id="editBannerUpdate" data-options="width:600,height:400,iconCls:'icon-add',title:'修改图片'"></div>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="openDeptDialog()">添加</a>

</div>

