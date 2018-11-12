<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#courseDg").datagrid({
           url:"${pageContext.request.contextPath}/Course/findAll",

            pagination:true,

           toolbar:'#tb',
           columns:[[
               {title:"id",field:'id',width:120,},
               {title:"功课标题",field:'title',width:120,},
               {title:"标记",field:'marking',width:120,},
               {title:"创建日期",field:'creatTime',width:220,
               },
               {title:'options',field:'options',width:200,
                   formatter:function(value,row,index){
                       return "<a href='javascript:;' class='options' onclick=\"delRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;" +
                           "<a href='javascript:;' class='options' onclick=\"openEditCourseUpdate('"+row.id+"')\" data-options=\"iconCls:'icon-edit',plain:true\">更新</a>";
                   }
               }
           ]],
        onLoadSuccess:function () {
            $(".options").linkbutton();
        },
        toolbar:'#tb',
        });
        });


    //删除一行的事件
    function delRow(id){
        //获取当前点击id发送ajax请求删除id这个人的信息
            $.post("${pageContext.request.contextPath}/Course/delete",{"id":id},function (result) {//响应成功之后回调

                if(result.success){
                    //提示信息
                    $.messager.show({title:'提示',msg:result.message});
                }else{
                    //提示信息
                    $.messager.show({title:'提示',msg:result.message});
                }
                //刷新datagrid数据
                $("#courseDg").datagrid('reload');//刷新当前datagrid
            });

    }

    function openCourseDialog(){
        $("#saveCourseDialog").dialog({
            href:'${pageContext.request.contextPath}/main/Course/save.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#saveCourseInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Course/save',
                        success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示信息
                                $.messager.show({title:'提示',msg:"功课添加成功!!!"});
                            }else{
                                //提示信息
                                $.messager.show({title:'提示',msg:resultObj.message});
                            }
                            //关闭对话框
                            $("#saveCourseDialog").dialog('close');
                            //刷新datagrid
                            $("#courseDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#saveArticleDialog").dialog('close');
                }
            }]
        });
    }

    function openEditCourseUpdate(id){
        $("#editCourseUpdate").dialog({
            href:'${pageContext.request.contextPath}/main/Course/edit.jsp?id='+id,
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#editCourseInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Course/update',
                        success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示信息
                                $.messager.show({title:'提示',msg:"功课修改成功!!!"});
                            }else{
                                //提示信息
                                $.messager.show({title:'提示',msg:resultObj.message});
                            }
                            //关闭对话框
                            $("#editCourseUpdate").dialog('close');
                            //刷新datagrid
                            $("#courseDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#editCourseUpdate").dialog('close');
                }
            }]
        });
    }
</script>
<table id="courseDg" class="easyui-datagrid" data-options="fit:true">


</table>
<%--保存用户对话框--%>
<div id="saveCourseDialog" data-options="width:600,height:400,iconCls:'icon-add',title:'添加功课'"></div>

<div id="editCourseUpdate" data-options="width:600,height:400,iconCls:'icon-add',title:'修改功课'"></div>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="openCourseDialog()">添加</a>
</div>

