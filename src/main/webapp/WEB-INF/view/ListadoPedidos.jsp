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

    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
    String mensaje = (String) request.getAttribute("mensaje");
%>

<html>
<head>
    <title>Listado de pedidos</title>
</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<%if(!pedidos.isEmpty()){%>
    <div class="container">
        <table class="table">
                <tr>
                    <th class="col">Id</th>
                    <th class="col">Usuario</th>
                    <th class="col">Listado Productos</th>
                    <th class="col">Fecha Creacion</th>
                    <th class="col">Estado</th>
                </tr>
                <%for(Pedido pedido:pedidos){%>
                <tr>
                    <td><%=pedido.getID()%></td>
                    <td><%=pedido.getUsuario().getNombre()%></td>
                    <td>
                        <ul>
                            <%for(ProductoaPedido productoDePedido: pedido.getProductos()){%>

                                <li><%=productoDePedido.getProducto().getNombre()%></li>

                            <%}%>
                        </ul>
                    </td>
                    <td><%=pedido.getFechaCreacion().toString()%></td>
                    <td><%=pedido.getEntrega()%></td>
                    <td></td>
                </tr>
                <%}%>
        </table>
    </div>
<%}else{%>
<h1><%=mensaje%></h1>
<%}%>
</body>
</html>
