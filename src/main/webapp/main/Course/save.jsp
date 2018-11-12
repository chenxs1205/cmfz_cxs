<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    /*$(function () {
        $("#cc").combobox({
            url:/Guru/FindAllName,
            valueField:'id',
            textField:'name',
            formatter: function(row){
                var opts = $(this).combobox('options');
                return row[opts.textField];
            }
        })
    })*/
</script>

<div style="text-align: center;">
    <form id="saveCourseInputForm" class="easyui-form" method="post" enctype="multipart/form-data">

        <div style="margin-top: 70px;">
            标题: <input type="text" name="title" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            标记: <input type="text" name="marking"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
        日期: <input type="text" name="creatTime"  class="easyui-datebox">
        </div>
    </form>
</div>