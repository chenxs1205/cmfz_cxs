<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;">
    <form id="saveChapterInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
       <%-- <input  name="id" hidden="hidden" value="${param.id}">--%>
        <div style="margin-top: 70px;">
            标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            下载路径: <input name="file"  class="easyui-filebox">
        </div>
           <div style="margin-top: 20px;">
               日期: <input type="text" name="uploadTime"  class="easyui-datebox">
           </div>
        <div style="margin-top: 70px;">
           <input hidden="hidden" value="${param.id}" name="album_id" data-options="required:true">
        </div>
    </form>
</div>