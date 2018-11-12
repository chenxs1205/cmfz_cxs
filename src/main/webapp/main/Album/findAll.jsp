<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#AlbumDg").treegrid({
           url:"${pageContext.request.contextPath}/Album/findAll",
            idField:'id',
            treeField:'title',
            fit:true,
            fitColumns:true,
           toolbar:'#albumTb',
           columns:[[
              /* {title:"id",field:'id',},*/
               {title:"标题",field:'title',width:20},
               /* {title:"发布时间",field:'publishDate',},
                {title:"数量",field:'count',},
                {title:"封面",field:'coverImg',},
                {title:"作者",field:'author', },
                {title:"播音",field:'broadCast', },
                {title:"简介",field:'brief', },*/
               {title:"下载路径",field:'downPath',width:20 },
               {title:"大小",field:'size', width:20},
               {title:"时长",field:'duration', width:20},


           ]],
        });
    });
    function addAlbumDialog(){
        $("#openAlbumAdd").dialog({
            href:'${pageContext.request.contextPath}/main/Album/save.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function(){
                    //保存用户信息
                    $("#saveAlbumInputForm").form('submit',{
                        url:'${pageContext.request.contextPath}/Album/save',
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
                            $("#openAlbumAdd").dialog('close');
                            //刷新datagrid
                            $("#AlbumDg").treegrid('reload');

                        }
                    });
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#openAlbumAdd").dialog('close');
                }
            }]
        });
    }

    function addChapterDetails(){
        var select = $("#AlbumDg").datagrid("getSelected");
        if(select!=null){
            $("#openChapterAdd").dialog({
                href:'${pageContext.request.contextPath}/main/Chapter/save.jsp?id='+select.id,
                buttons:[{
                    iconCls:'icon-save',
                    text:'添加',
                    handler:function(){
                        //保存用户信息
                        $("#saveChapterInputForm").form('submit',{
                            url:'${pageContext.request.contextPath}/Chapter/save',
                            success:function(result){//响应的一定是json格式字符串   使用应该先转为js对象
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    //提示信息
                                    $.messager.show({title:'提示',msg:"章节添加成功!!!"});
                                }else{
                                    //提示信息
                                    $.messager.show({title:'提示',msg:resultObj.message});
                                }
                                //关闭对话框
                                $("#openChapterAdd").dialog('close');
                                //刷新datagrid
                                $("#AlbumDg").treegrid('reload');
                            }
                        });
                    }
                },{
                    iconCls:'icon-cancel',
                    text:'关闭',
                    handler:function(){
                        $("#openChapterAdd").dialog('close');
                    }
                }]
            });
        }else {
            $.messager.show({title:'提示',msg:"必须指定一个章节!!!"});
        }

    }


    //专辑详情
    function albumDetails() {
        var roots = $("#AlbumDg").treegrid("getRoots");
        var select = $("#AlbumDg").datagrid("getSelected");
        console.log(select);
        if(select==null){
            $.messager.show({title:'提示',msg:"至少选中一行!!!"});
        }else{
            var index=$.inArray(select,roots);
            if(index!=-1){
                $("#openAlbum").dialog({
                    href:'${pageContext.request.contextPath}/main/Album/AlbumDetails.jsp?id='+select.id,
                });
             }else{
                $.messager.show({title:'提示',msg:"请选择专辑!!!"});
            }
        }
    }
/*  下载音频*/
    function downChapterload() {
        var roots = $("#AlbumDg").treegrid("getRoots");
        var select = $("#AlbumDg").datagrid("getSelected");
        console.log(select);
        if(select==null){
            $.messager.show({title:'提示',msg:"至少选中一个文件!!!"});
        }else{
            var index=$.inArray(select,roots);
            if(index==-1){
                $("#down").attr('href',"${pageContext.request.contextPath}/Chapter/download?downPath="+select.downPath+"&openStyle=attachment");
            }else{
                $.messager.show({title:'提示',msg:"请选择章节下载!!!"});
            }

        }
    }

</script>
<table id="AlbumDg" ></table>

<div id="albumTb">
    <a href="#" class="easyui-linkbutton" onclick="albumDetails()" data-oprions="iconCls:'icon-edit',plain:true">专辑详情</a>
    <a href="#" class="easyui-linkbutton" onclick="addAlbumDialog()" data-oprions="iconCls:'icon-save',plain:true">添加专辑</a>
    <a href="#" class="easyui-linkbutton" onclick="addChapterDetails()" data-oprions="iconCls:'icon-save',plain:true">添加章节</a>
    <a href="#" id="down" class="easyui-linkbutton" onclick="downChapterload()" data-oprions="iconCls:'icon-edit',plain:true">下载音频</a>


</div>
<%--专辑详情对话框--%>
<div id="openAlbum" data-options="resizable:true,width:500,height:550,iconCls:'icon-save',title:'专辑详情',collapsible:true,minimizable:true,maximizable:true,"></div>
<%--添加专辑对话框--%>
<div id="openAlbumAdd" data-options="resizable:true,width:500,height:550,iconCls:'icon-save',title:'添加专辑',collapsible:true,minimizable:true,maximizable:true,"></div>
<%--添加章节对话框--%>
<div id="openChapterAdd" data-options="resizable:true,width:500,height:550,iconCls:'icon-save',title:'添加章节',collapsible:true,minimizable:true,maximizable:true,"></div>

