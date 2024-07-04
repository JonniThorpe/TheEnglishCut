<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.gt.theenglishcut.ui.producto" %>
<%@ page import="com.gt.theenglishcut.entity.Categoria" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    producto producto = (com.gt.theenglishcut.ui.producto) request.getAttribute("producto");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");

%>
<html>
<head>
    <title></title>

</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<h1 align="center">CREAR PRODUCTO</h1>
<div class="container-sm">
    <form:form action="/guardarProducto" method="post" modelAttribute="producto">
        <form:hidden path="idProducto"/>
        <div class="mb-3">
            <label >Nombre del producto</label>
            <from:input path="nombre" class="col-md-8 form-control input-md"/>
        </div>
        <div class="mb-3">
            <label for="descripcionProducto">Descripcion del producto</label>
            <form:textarea id="descripcionProducto" path="descripcion" class="form-control" rows="3"></form:textarea>
        </div>
        <div>
            <label for="categoriasID">Categorias Disponibles</label>
            <form:select id="categoriasID" path="categorias" items="${categorias}" itemLabel="nombre" itemValue="ID" multiple="true"/>
        </div>
        <div class="mb-3">
            <label>Precio del producto</label>
            <form:input path="precio" class="col-md-8 form-control input-md" />
        </div>
        <div class="form-group mb-3">
            <label for="cantidadProducto">Cantidad</label>
            <form:select path="cantidad" class="form-control" id="cantidadProducto">
                <%--TODO set selected cantidad--%>
                <%
                for (int i = 0; i < 100; i++) {
                String selected = "";
                    %>
                <option value="<%=i%>"><%=i%></option>
                         <%
                    }%>
            </form:select>
        </div>
        <div class="form-group mb-3">
            <label >Imagen</label>
            <form:input path="imagen" type="text" class="form-control" />
        </div>
        <div class="input-group flex-nowrap mb-3">
            <button type="submit" class="btn btn-primary" >Enviar</button>
        </div>
        <a href="/" class="btn btn-primary ">Volver</a>
    </form:form>
</div>
</body>
</html>
