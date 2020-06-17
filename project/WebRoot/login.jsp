<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<script type="text/javascript">
function beforeSubmit(form){
if(form.username.value==''){
alert('用户名不能为空！');
form.username.focus();
return false;
}
if(form.password.value==''){
alert('密码不能为空！');
form.password.focus();
return false;
}
}
</script>
<html >
<head>

  <title>学习资源平台</title>

  <link rel="stylesheet" href="css/amazeui.min.css">
  <link rel="stylesheet" href="css/app.css">
</head>
<body>
<div class="am-g myapp-login">
	<div class="myapp-login-logo-block">
		<div class="myapp-login-logo">
			<i class="am-icon-jsfiddle"></i>
		</div>
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				Js<span>Fiddle</span>
				<div class="info">Find the best places in town.</div>
			</div>
		</div>

		<div class="login-font">
			<i>Log In </i> 
		</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form" action="loginController/login.do" method="post" onSubmit="return beforeSubmit(this);">
				<fieldset>
					<div class="am-form-group">
						<input type="text"  placeholder="输入用户名" name="name">
					</div>

					<div class="am-form-group">
						<input type="password"  placeholder="输入密码" name="password">
					</div>
                         <p> <select name="selecttype">           
  							 <option  value ="1">学生</option>
  							 <option  value ="2">教师</option>
 							 <option  value ="3">管理员</option>
					       </select> </p>
					<p><button type="submit" class="am-btn am-btn-default">Log In</button></p>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<div style="text-align:center;">
<a href="zhuce.jsp" >如果你没有账号，请点击</a>
</div> 

</body>
</html>