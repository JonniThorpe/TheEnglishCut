<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 27/03/2024
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
<%@ include file = "componentes/Navbar.jsp" %>
<h1>HOLA MUNDO</h1>
<div class="container-sm">
    <form action="/detalle/producto/crearProducto" method="post" modelAttribute="producto">
        <div class="form-group">
            <label for="exampleFormControlInput1">Nombre del producto</label>
            <input name="nombre" type="text" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Descripcion del producto</label>
            <textarea name="descripcion"  class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">$</span>
            </div>
            <input name="precio" type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
            <div class="input-group-append">
                <span class="input-group-text">.00</span>
            </div>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect1">Cantidad</label>
            <select name="cantidad" class="form-control" id="exampleFormControlSelect1">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlFile1">imagen</label>
            <input name="img" type="file" class="form-control-file" id="exampleFormControlFile1">
        </div>
        <div class="input-group flex-nowrap">
            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
        <a href="home.jsp" class="btn btn-primary">Volver</a>
    </form>
</div>
</body>
</html>
