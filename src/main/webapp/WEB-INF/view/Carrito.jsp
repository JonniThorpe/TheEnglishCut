<%@ page import="com.gt.theenglishcut.entity.Producto" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

Map<Producto, Integer> productosCarrito = (Map<Producto, Integer>) request.getAttribute("productosCarrito");
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
    <form method="post" action="confirmarPedido">

        <div class="container-fluid">
            <table class="table">
                <tr>
                    <th></th>
                    <th class="col">Nombre</th>
                    <th class="col">Precio/Unidad</th>
                    <th class="col">Número Unidades</th>
                    <th class="col">Precio Total</th>
                </tr>
                <%

                double total = 0;
                int num_productos;
                    for(Map.Entry<Producto, Integer> mapaProducto: productosCarrito.entrySet()){
                        Producto producto = mapaProducto.getKey();
                        num_productos= mapaProducto.getValue();

                double precio = producto.getPrecio();
                double precio_total=precio * num_productos;
                %>
                <tr>
                    <td><img src="../../img/<%=producto.getImagen()%>" width="64" height="64"></td>
                    <td><%=producto.getNombre()%></td>
                    <td><%=producto.getPrecio()%>€</td>
                    <td>
                        <select name="cantidad" id="unidades">
                            <%
                                for (int i = 0; i <= num_productos; i++) {
                                    String selected = "";
                                    if(i == num_productos){
                                        selected = "selected";
                                    }
                            %>
                            <option value="<%=i%>" <%=selected%> ><%=i%></option>
                            <%
                                }%>
                        </select>
                        <label for="unidades">uds</label></td>
                    <td><%=precio_total%></td>
                </tr>
                <%total = total+precio_total;}%>
                <tr>
                    <td colspan="2">Total</td>
                    <td ><%=total%>€</td>
                </tr>
            </table>
            <button type="submit" class="btn btn-primary">Realizar pedido </button>
        </div>
    </form>
</body>
</html>
