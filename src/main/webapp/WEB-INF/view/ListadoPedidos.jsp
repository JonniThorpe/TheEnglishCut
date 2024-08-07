<%@ page import="com.theenglishcut.entity.OrderEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.theenglishcut.entity.ProductToOrderEntity" %>
<%@ page import="com.theenglishcut.entity.UserEntity" %>
<%@ page import="com.theenglishcut.entity.OrderEntity" %>
<%@ page import="com.theenglishcut.entity.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    List<OrderEntity> orders = (List<OrderEntity>) request.getAttribute("orders");
    List<UserEntity> users = (List<UserEntity>) request.getAttribute("users");
    String mensaje = (String) request.getAttribute("mensaje");

    String tipoUsuario = (String)request.getAttribute("tipoUsuario");


%>

<html>
<head>
    <title>Listado de orderEntities</title>
</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<%if(!orders.isEmpty()){%>
    <div class="container mt-3">
        <%if(tipoUsuario.equals("Administrador")){%>
        <div>
            <form action="filtrar" method="post" >
                <label for="clientes">Nombre del cliente</label>
                <select id="clientes" name="idUsuario">
                    <option value="0"></option>
                    <%for(UserEntity userioFiltro: users){%>
                    <option value="<%=userioFiltro.getID()%>"><%=userioFiltro.getNombre()%></option>
                    <%}%>
                </select>
                <button type="submit" class="btn btn-primary">enviar</button>
            </form>
        </div>
        <%}%>
        <table class="table">
                <tr>
                    <th class="col">Id</th>
                    <th class="col">Usuario</th>
                    <th class="col">Listado Productos</th>
                    <th class="col">Fecha Creacion</th>
                    <th class="col">Estado</th>
                </tr>
                <%for(OrderEntity orderEntity : orders){%>
                <tr>
                    <td><%=orderEntity.getID()%></td>
                    <td><%=orderEntity.getUsuario().getNombre()%></td>
                    <td>
                        <ul>
                            <%for(ProductToOrderEntity productoDePedido: orderEntity.getProductos()){%>

                                <li>
                                    <%=productoDePedido.getProducto().getNombre()%>,
                                    <%=productoDePedido.getCantidad()%> Unds</li>

                            <%}%>
                        </ul>
                    </td>
                    <td><%=orderEntity.getFechaCreacion().toString()%></td>
                    <td><%=orderEntity.getEntrega()%></td>
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
