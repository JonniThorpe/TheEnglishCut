<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 02/07/2024
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductEntity product = (ProductEntity) request.getAttribute("product");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<div class="h5">DetalleProducto</div>
<div class="">

</div>
</body>
</html>
