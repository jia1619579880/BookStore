<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--	引入包含头部css、jQuery、base标签 静态引入--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详细</span>

    <%--			静态引入 manager管理模块的菜单--%>
    <%@include file="/pages/common/manager_menu.jsp"%>

</div>

<div id="main">
    <table>

        <c:if test="${empty sessionScope.orderItems}">
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>总价</td>
                <td>操作</td>
            </tr>
            <tr>
                <td colspan="5"><a href="pages/client/index.jsp">亲, 该订单不存在!</a> </td>
            </tr>
        </c:if>
    </table>
        <%--订单存在--%>
    <table>
        <c:if test="${not empty sessionScope.orderItems}">

                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>总价</td>
                </tr>
                <c:forEach var="item" items="${sessionScope.orderItems}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.count}</td>
                        <td>${item.price}</td>
                        <td>${item.totalPrice}</td>
                    </tr>
                </c:forEach>


            <tr>
                <div class="order_info">
                    <td><span class="order_span">总金额<span class="b_price">${sessionScope.order.status}</span>元</span></td>
                    <td><span class="order_span">订单编号<span class="b_price">${sessionScope.order.orderId}</span></span></td>
                    <td><span class="order_span">创建时间<span class="b_price">${sessionScope.order.createTime}</span></span></td>
                    <td><span class="order_span">订单状态<span class="b_price"></td>
                    <c:if test="${sessionScope.order.status==0}">
                        <td><span>未发货</span></td>
                    </c:if>
                    <c:if test="${sessionScope.order.status==1}">
                        <td><span>已发货</span></td>
                        <td><span class="order_span"><a
                                href="orderServlet?action=receiveOrder&orderId=${sessionScope.order.orderId}">确认收货
                        </a></span></td>
                    </c:if>
                    <c:if test="${sessionScope.order.status==2}">
                        <td><span>已收货</span></td>
                    </c:if>
                </span></span><br/>
                </div>
            </tr>
        </c:if>

    </table>
</div>

<%--	静态引入页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>

</body>
</html>
