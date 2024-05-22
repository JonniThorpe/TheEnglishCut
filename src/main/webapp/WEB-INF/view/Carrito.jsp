<%@ page import="com.gt.theenglishcut.entity.Producto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
List<Producto> productosCarrito = (List<Producto>) request.getAttribute("productosCarrito");
%>
<html>
<head>
    <title>Title</title>
</head>
<!--
TODO Stackear producto en cantidad y precio si son el mismo tipo de producto
El listado de productos muestra cada producto de la lista productosCarrito definida en la parte superior de esta pagina,
es necesario que aquel producto que este en la lista varias veces se muestre en una sola fila en lugar de varias,
donde aparezca ese mismo producto y el numero de veces que aparece en el carrito y un sumatorio del precio del producto
-->
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
    <form method="post" action="/confirmarPedido">
        <input hidden name="listaProductos" value="<%=productosCarrito%>"/>
        <div class="container-fluid">
            <table class="table">
                <tr>
                    <th class="col">Nombre</th>
                    <th class="col">Precio</th>
                </tr>
                <%
                double total = 0;
                    for(Producto producto: productosCarrito){
                double precio = producto.getPrecio();
                %>
                <tr>
                    <td><%=producto.getNombre()%></td>
                    <td><%=producto.getPrecio()%></td>
                </tr>
                <%total = total+precio;}%>
                <tr>
                    <td>Total</td>
                    <td><%=total%></td>
                </tr>
            </table>
            <button type="button" class="btn btn-primary">Realizar pedido </button>
        </div>
    </form>
</body>
</html>
