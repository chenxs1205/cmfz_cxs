<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#guruDg").datagrid({
           url:"${pageContext.request.contextPath}/Guru/findAll",

            pagination:true,

           toolbar:'#tb',
           columns:[[
               {title:"id",field:'id',width:120,},
               {title:"名字",field:'name',width:120,},
               {title:"头像",field:'headPic',width:120,},
               {title:"性别",field:'sex',width:120,},
               {title:'按钮',field:'options',width:200,
                   formatter:function(value,row,index){
                       return "<a href='javascript:;' class='options' onclick=\"delRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;"
                   }
               }
           ]],

            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/guru/' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.sex + '</p>' +
                    '<p>Status: ' + rowData.name + '</p>' +
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
        $.post("${pageContext.request.contextPath}/Guru/delete",{"id":id},function (result) {//响应成功之后回调
            //刷新datagrid数据
            $("#guruDg").datagrid('reload');//刷新当前datagrid
        });
    }

    function openGuruDialog(){
        $("#saveGuruDialog").dialog({
            href:'${pageContext.request.contextPath}/main/Guru/save.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#saveGuruInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Guru/save',
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
                            $("#saveGuruDialog").dialog('close');
                            //刷新datagrid
                            $("#guruDg").datagrid('reload');
                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#saveGuruDialog").dialog('close');
                }
            }]
        });
    }
</script>
<table id="guruDg" class="easyui-datagrid" data-options="fit:true">


</table>
<%--保存用户对话框--%>
<div id="saveGuruDialog" data-options="width:600,height:400,iconCls:'icon-add',title:'添加上师'"></div>

<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="openGuruDialog()">添加</a>

</div>

