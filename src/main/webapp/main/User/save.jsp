<%@page pageEncoding="UTF-8" %>
<div style="text-align: center;">
    <form id="saveUserInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 70px;">
            手机号: <input type="text" name="phoneNum" class="easyui-textbox" data-options="required:true">
        </div>
        <div style="margin-top: 20px;">
            用户名称: <input type="text" name="username"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户密码: <input type="text" name="password"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户省份: <input type="text" name="province"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户城市: <input type="text" name="city"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户法名: <input type="text" name="nickName"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户性别: <input type="text" name="gender"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户签名: <input type="text" name="sign"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            用户头像: <input name="file"  class="easyui-filebox">
          </div>
        <div style="margin-top: 20px;">
            状态: <input type="text" name="status"  class="easyui-textbox">
        </div>
        <div style="margin-top: 20px;">
            日期: <input type="text" name="userdate"  class="easyui-datebox">
         </div>
    </form>
</div>