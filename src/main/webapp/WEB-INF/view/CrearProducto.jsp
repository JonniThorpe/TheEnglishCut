<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.gt.theenglishcut.ui.producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    producto producto = (com.gt.theenglishcut.ui.producto) request.getAttribute("producto");
    int cantidadProducto = (int) request.getAttribute("cantidadProducto");

%>
<html>
<head>
    <title></title>
</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<h1>Crear producto</h1>
<div class="container-sm">
    <form:form action="/guardarProducto" method="post" modelAttribute="producto">
        <div class="mb-3">
            <label >Nombre del producto</label>
            <from:input path="nombre" class="col-md-8 form-control input-md"/>
        </div>
        <div class="mb-3">
            <label>Descripcion del producto</label>
            <form:textarea path="descripcion" class="form-control" rows="3"></form:textarea>
        </div>
        <div class="mb-3">
            <label>Precio del producto</label>
            <form:input path="precio" class="col-md-8 form-control input-md" />
        </div>
        <div class="form-group mb-3">
            <label for="exampleFormControlSelect1">Cantidad</label>
            <form:select path="cantidad" class="form-control" id="exampleFormControlSelect1">
                <%
                    for (int i = 0; i < 100; i++) {
                    %>
                <option value="<%=i%>"><%=i%></option>
                         <%
                    }%>
            </form:select>
        </div>
        <div class="form-group mb-3">
            <label >imagen</label>
            <form:input path="imagen" type="text" class="form-control" />
        </div>
        <div class="input-group flex-nowrap mb-3">
            <button type="submit" class="btn btn-primary ">Enviar</button>
        </div>
        <a href="home.jsp" class="btn btn-primary ">Volver</a>
    </form:form>
</div>
</body>
</html>
