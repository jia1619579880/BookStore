<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	引入包含头部css、jQuery、base标签 静态引入--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#clearCart").click(function () {
				return confirm("确定要清空购物车吗？");
			});

			$("a.deleteCartBtn").click(function () {
				// 确认是否删除
				return confirm("确认是否删除[" + $(this).parent().parent().find("td:first").text() + "]?");
			});

			$(".updateCount").change(function () {
				// 获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				var id = $(this).attr('bookId');
				// 获取商品数量
				var count = this.value;
				if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
					//发起请求。给服务器保存修改
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;
				} else {
					// defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%-- 登陆成功页面 静态引入 --%>
		<%@include file="/pages/common/login_seccess_menu.jsp"%>
	</div>


	<div id="main">



		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
<%--			判断购物车为空的情况--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，您当前的购物车为空!快跟小伙伴们去浏览商品把！！！</a> </td>

				</tr>
			</c:if>
<%--			判断购物车不为空--%>
			<c:if test="${not empty sessionScope.cart.items}">

			</c:if>

			<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.value.name}</td>
					<td>
						<input style="width: 80px" bookId="${item.value.id}" class="updateCount" type="text" value="${item.value.count}">
					</td>
					<td>${item.value.price}</td>
					<td>${item.value.totalPrice}</td>
					<td><a class="deleteCartBtn" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--	静态引入页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>