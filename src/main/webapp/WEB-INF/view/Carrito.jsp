<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="java.util.*" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

Map<ProductEntity, Integer> productosCarrito = (Map<ProductEntity, Integer>) request.getAttribute("productosCarrito");
%>
<html>
<head>
    <title>Title</title>
</head>
<!--
TODO Stackear productEntity en cantidad y precio si son el mismo tipo de productEntity
El listado de productEntities muestra cada productEntity de la lista productosCarrito definida en la parte superior de esta pagina,
es necesario que aquel productEntity que este en la lista varias veces se muestre en una sola fila en lugar de varias,
donde aparezca ese mismo productEntity y el numero de veces que aparece en el carrito y un sumatorio del precio del productEntity
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
                    for(Map.Entry<ProductEntity, Integer> mapaProducto: productosCarrito.entrySet()){
                        ProductEntity productEntity = mapaProducto.getKey();
                        num_productos= mapaProducto.getValue();

                double precio = productEntity.getPrecio();
                double precio_total=precio * num_productos;
                %>
                <tr>
                    <td><img src="../../img/<%=productEntity.getImagen()%>" width="64" height="64"></td>
                    <td><%=productEntity.getNombre()%></td>
                    <td><%=productEntity.getPrecio()%>€</td>
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
            <button type="submit" class="btn btn-primary">Realizar orderEntity </button>
        </div>
    </form>
</body>
</html>
