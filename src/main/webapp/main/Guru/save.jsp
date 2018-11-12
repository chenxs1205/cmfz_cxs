<%@page pageEncoding="UTF-8" %>
<div style="text-align: center;">
    <form id="saveGuruInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${param.id}">
        <div style="margin-top: 70px;">
            名字: <input type="text" name="name" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            头像: <input name="file"  class="easyui-filebox">
        </div>
        <div style="margin-top: 20px;">
            性别: <input type="text" name="sex"  class="easyui-textbox">
        </div>
    </form>
</div>