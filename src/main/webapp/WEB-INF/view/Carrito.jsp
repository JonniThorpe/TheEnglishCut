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
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
    <form method="post" action="/confirmarPedido">
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
