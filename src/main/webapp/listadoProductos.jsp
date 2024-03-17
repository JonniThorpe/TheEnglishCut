<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Producto</title>
</head>
<body>

<%@ include file = "componentes/Navbar.jsp" %>

<div class="p-5">
    <h1>Nuestra mejor coleccion de pata negra</h1>
    <label>De esa forma, comúnmente podemos decir que un jamón 'pata negra'
        es aquel que procede de cerdos ibéricos 100% bellota, aunque es mucho
        más correcto denominarlo como jamón ibérico puro de bellota 100%</label>
</div>
<!--
El siguiente codigo genera el listado de productos segun el tamaño del bucle
TODO en lugar de un numero, debe ser numero de productos en la bbdd
TODO el maximo por fila debe ser 5 productos despues salta a la siguiente  fila
-->
<div class="container text-center">
    <div class="row row-cols-auto">
        <% for (int i = 0; i < 16; i++) {%>
        <div class="col mb-3">
            <%@ include file = "componentes/cardProducto.jsp" %></div>
        <%}%>
    </div>
</div>
</body>
</html>
