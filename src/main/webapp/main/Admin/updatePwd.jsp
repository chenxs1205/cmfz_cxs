<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script>
</script>
<div style="text-align: center;">
    <form id="editAdminUpdatePwd" class="easyui-form" method="post">
        <input type="hidden" name="id" value="${param.id}">
                <div style="margin-top: 70px;">
                   原密码: <input type="text" id="Pwd1" class="easyui-textbox" data-options="required:true">
                </div>
                <div style="margin-top: 20px;">
                    新密码: <input type="text" id="Pwd2"  class="easyui-textbox">
                </div>
                <div style="margin-top: 20px;">
                    确定密码: <input type="password" name="password" id="Pwd3" class="easyui-textbox">
                </div>
            </form>
        </div>