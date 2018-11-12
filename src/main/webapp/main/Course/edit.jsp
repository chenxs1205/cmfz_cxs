<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        console.log("${param.id}");
        $("#editCourseInputForm").form('load','${pageContext.request.contextPath}/Course/queryOne?id=${param.id}');
    })
</script>
<div style="text-align: center;">
    <form id="editCourseInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <input type="text" name="id"  >
                <div style="margin-top: 70px;">
                    标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
                </div>
                <div style="margin-top: 20px;">
                    标记: <input type="text" name="marking"  class="easyui-textbox">
                </div>
                <div style="margin-top: 20px;">
                    创建日期: <input type="text" name="creatTime"  class="easyui-datebox">
                </div>
            </form>
        </div>


