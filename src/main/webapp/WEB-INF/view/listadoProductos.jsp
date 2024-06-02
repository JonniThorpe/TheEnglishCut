<%@ page import="com.gt.theenglishcut.entity.Producto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");

    String mensaje = (String) request.getAttribute("mensaje");

%>

<html>
<head>
    <title>Producto</title>
</head>
<body>

    <%@ include file = "../componentes/Navbar.jsp" %>

    <div class="p-5">
        <h1>Nuestra mejor coleccion productoss</h1>
        <label>
            De esa forma, comúnmente podemos decir que un jamón 'pata negra'
            es aquel que procede de cerdos ibéricos 100% bellota, aunque es mucho
            más correcto denominarlo como jamón ibérico puro de bellota 100%
        </label>
        <%if(usuario.isEmpty()){%>
        <div class="row">
            <div class="col">
                <img src="../../iconos/info.png" class="img-thumbnail" alt="...">
                <div>
                    <p>
                        No olvide hacer <b>Login</b> para poder realizar pedidos en nuestra web
                    </p>

                </div>
            </div>
        </div>
        <%} else if (tipo.equals("Usuario")) {%>
        <div>
            <p>
                <br/><b>pulse el boton asignar producto y luego en el icono carrito podra realizar su pedido.
                !Gracias!</b>
            </p>
        </div>
        <%}%>
        <%if(tipo.equals("Administrador")){%>
        <div>
            <a class="btn btn-primary" href="/CrearProducto">crear Producto</a>
        </div>
        <%}%>
    </div>
    <%if(!productos.isEmpty()){%>
    <!--
    El siguiente codigo genera el listado de productos segun el tamaño del bucle
    TODO en lugar de un numero, debe ser numero de productos en la bbdd
    TODO el maximo por fila debe ser 5 productos despues salta a la siguiente  fila
    -->
    <div class="container text-center">
        <div class="row row-cols-3">
            <% for (Producto producto : productos) {%>
            <div class="col col-12 col-sm-8 col-md-6 col-lg mb-3">
                <div class="card " style="width: 18rem;">
                    <img src="../../img/<%=producto.getImagen()%>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title"><%=producto.getNombre()%></h5>
                        <p class="card-text"><%=producto.getDescripcion()%></p>
                        <%if(!usuario.isEmpty() && !tipo.equals("Administrador")){%>
                            <a href="/addProducto?id=<%=producto.getID()%>" class="btn btn-primary">Asignar producto al carrito</a>
                        <%}%>
                        <div class="fw-bold">Cantidad disponible: <%=producto.getInventario().getCantidad()%></div>
                        <%if(tipo.equals("Administrador")){%>
                            <div>
                                <a href="/eliminarProducto?id=<%=producto.getID()%>">eliminar Producto</a>
                            </div>
                        <%}%>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
    <%}else{%>
        <h1><%=mensaje%></h1>
    <%}%>
</body>
</html>
