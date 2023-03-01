<%--
  Created by IntelliJ IDEA.
  User: oliverdai
  Date: 2/14/23
  Time: 9:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--  分页开始  --%>
<div id="page_nav">
  <%--	在显示首页的时候，就不限时上一页	--%>
  <c:if test="${requestScope.page.pageNo >1}">
    <a href="${requestScope.page.url}&pageNo=1">首页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
  </c:if>


  <c:choose>
    <c:when test="${requestScope.page.pageTotal <= 5}">
      <c:set var="begin" value="1"/>
      <c:set var="end" value="${requestScope.page.pageTotal}"/>

    </c:when>

    <c:when test="${requestScope.page.pageTotal > 5}">
      <c:choose>
        <c:when test="${requestScope.page.pageNo <= 3}">
          <c:set var="begin" value="1"/>
          <c:set var="end" value="5"/>
        </c:when>
        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
          <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
          <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <c:otherwise>
          <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
          <c:set var="end" value="${requestScope.page.pageNo+2}"/>

        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>

  <c:forEach begin="${begin}" end="${end}" var="i">
    <c:if test="${i == requestScope.page.pageNo}">
      【${i}】
    </c:if>
    <c:if test="${i != requestScope.page.pageNo}">
      <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
    </c:if>
  </c:forEach>

  <%--	在显示末尾的时候，就不限时下一页	--%>
  <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
  </c:if>
  共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
  到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
  <input id="serchPageBtn" type="button" value="确定">


</div>

<script type="text/javascript">
  $(function () {
    $("#serchPageBtn").click(function () {

      var pageNo = $("#pn_input").val();

      var pageTatol = ${requestScope.page.pageTotal};

      if (pageNo < pageTatol && pageNo > 0) {
        location.href = "${requestScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
      }

    });
  });
</script>
<%--  分页结束  --%>