<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#cc").combobox({
            url:'${pageContext.request.contextPath}/Guru/FindAllName',
            valueField:'id',
            textField:'name',
            formatter: function(row){
                var opts = $(this).combobox('options');
                return row[opts.textField];
            }
        })
    })
</script>

<div style="text-align: center;">
    <form id="saveArticleInputForm" class="easyui-form" method="post" enctype="multipart/form-data">

        <div style="margin-top: 70px;">
            标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            图片路径: <input name="file"  class="easyui-filebox">
        </div>
        <div style="margin-top: 20px;">
            内容: <input type="text" name="content"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
        日期: <input type="text" name="publishDate"  class="easyui-datebox">
        </div>

        <div style="margin-top: 20px;">
            选择上师: <input type="text" name="fid"  class="easyui-combobox" id="cc">
        </div>
    </form>
</div>