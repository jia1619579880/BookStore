<%--
  Created by IntelliJ IDEA.
  User: oliverdai
  Date: 2/10/23
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>、

<%
    String basePath = ""+request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>

<base href="<%=basePath%>">
<script type="text/javascript" src="static/script/jquery-3.5.1.js"></script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
