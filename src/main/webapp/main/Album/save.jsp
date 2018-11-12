<%@page pageEncoding="UTF-8" %>
<div style="text-align: center;">
    <form id="saveAlbumInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${param.id}">
        <div style="margin-top: 70px;">
            标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            数量: <input type="text" name="count"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            发布时间: <input name="publishDate"  class="easyui-datebox">
        </div>
        <div style="margin-top: 20px;">
            封面: <input name="file"  class="easyui-filebox">
        </div>
        <div style="margin-top: 20px;">
            分数: <input type="text" name="star"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            作者: <input type="text" name="author"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            播音: <input type="text" name="broadCast"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            简介: <input type="text" name="brief"  class="easyui-textbox">
        </div>
    </form>
</div>