<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%--	引入包含头部css、jQuery、base标签 静态引入--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
	<script type="text/javascript">


		$(function () {
			$("#code_img").click(function () {
				this.src = "${bathPath}kaptcha.jpg?d="+new Date();
			});

			$("#sub_btn").click(function () {
				// 得到用户名输入框的值
				var usernameText = $("#username").val();
				var usernamePatth = /^\w{5,12}$/;

				if (!usernamePatth.test(usernameText)){
					$("span.errorMsg").text("用户名不合法");

					return false;
				}

				// get password from textbox

				var passwordText = $("#password").val();
				var passwordPatth = /^\w{5,12}$/;

				if (!passwordPatth.test(passwordText)){
					$("span.errorMsg").text("密码不合法");

					return false;
				}

				var repwdText = $("#repwd").val();
				if (repwdText!==passwordText){
					$("span.errorMsg").text("两次密码不一致");

					return false;
				}

				// get the email from textbox
				var emailText = $("#email").val();
				var emailPatth = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

				if (!emailPatth.test(emailText)){
					$("span.errorMsg").text("邮箱不合法");

					return false;
				}

				// get the confirm from textbox
				var codeText = $("#code").val();

				codeText= codeText.trim();

				if(codeText==null||codeText===""){
					$("span.errorMsg").text("验证码不能为空");

					return false;
				}


				$("span.errorMsg").text("");
			});
		});

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
											value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px;height: 30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									<input type="hidden" name="action" value="regist"/>

								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--	静态引入页脚--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>