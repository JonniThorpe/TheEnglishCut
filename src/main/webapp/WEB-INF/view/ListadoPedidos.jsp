<%@ page import="com.gt.theenglishcut.entity.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gt.theenglishcut.entity.ProductoaPedido" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("Pedidos");

%>

<html>
<head>
    <title>Listado de pedidos</title>
</head>
<body>

<%@ include file = "../componentes/Navbar.jsp" %>

<tr>
    <th>Id</th>
    <th>Usuario</th>
    <th>Listado Productos</th>
    <th>Fecha Creacion</th>
    <th>Entrega</th>
</tr>
<%for(Pedido pedido:pedidos){%>
<tr>
    <td><%=pedido.getUsuario()%></td>
    <td>
        <ul>
            <%for(ProductoaPedido producto: pedido.getProductos()){%>

                <li><%=producto.getProducto().getNombre()%></li>

            <%}%>
        </ul>
    </td>
    <td><%=pedido.getFechaCreacion().toString()%></td>
    <td><%=pedido.getEntrega()%></td>
    <td></td>
</tr>
<%}%>
</body>
</html>
