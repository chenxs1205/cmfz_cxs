<%@page pageEncoding="UTF-8" %>
<div style="text-align: center;">
    <form id="saveBannerInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${param.id}">
        <div style="margin-top: 70px;">
            标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            图片路径: <input name="file"  class="easyui-filebox">
        </div>
        <div style="margin-top: 20px;">
            描述: <input type="text" name="description"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            状态: <input type="text" name="status"  class="easyui-textbox">
        </div> <div style="margin-top: 20px;">
        日期: <input type="text" name="date"  class="easyui-datebox">
    </div>


    </form>
</div>