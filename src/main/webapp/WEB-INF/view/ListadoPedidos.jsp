<%@ page import="com.gt.theenglishcut.entity.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gt.theenglishcut.entity.ProductoaPedido" %>
<%@ page import="com.gt.theenglishcut.entity.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    String mensaje = (String) request.getAttribute("mensaje");

    String tipoUsuario = (String)request.getAttribute("tipoUsuario");


%>

<html>
<head>
    <title>Listado de pedidos</title>
</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<%if(!pedidos.isEmpty()){%>
    <div class="container mt-3">
        <%if(tipoUsuario.equals("Administrador")){%>
        <div>
            <form action="Orders/filtrar" method="post" >
                <label for="clientes">Nombre del cliente</label>
                <select id="clientes" name="idUsuario">
                    <option value="0"></option>
                    <%for(Usuario userioFiltro:usuarios){%>
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
                <%for(Pedido pedido:pedidos){%>
                <tr>
                    <td><%=pedido.getID()%></td>
                    <td><%=pedido.getUsuario().getNombre()%></td>
                    <td>
                        <ul>
                            <%for(ProductoaPedido productoDePedido: pedido.getProductos()){%>

                                <li>
                                    <%=productoDePedido.getProducto().getNombre()%>,
                                    <%=productoDePedido.getCantidad()%> Unds</li>

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
