<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ProductEntity> productEntities = (List<ProductEntity>) request.getAttribute("products");

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
        <%if(userEntity == null){%>
        <div class="row">
            <div class="col">
                <img src="../../iconos/info.png" class="img-thumbnail" alt="...">
                <div>
                    <p>
                        No olvide hacer <b>Login</b> para poder realizar orderEntities en nuestra web
                    </p>

                </div>
            </div>
        </div>
        <%} else if (tipo.equals("Usuario")) {%>
        <div>
            <p>
                <br/><b>pulse el boton asignar productEntity y luego en el icono carrito podra realizar su orderEntity.
                !Gracias!</b>
            </p>
        </div>
        <%}%>
        <%if(tipo.equals("Administrador")){%>
        <div>
            <a name="crearProducto" class="btn btn-primary" href="/CrearProducto">crear Producto</a>
        </div>
        <%}%>
    </div>
    <%if(!productEntities.isEmpty()){%>
    <div class="container text-center">
        <div class="row row-cols-xxl-4">
            <% for (ProductEntity productEntity : productEntities) {%>
            <div class="col mt-4">
                <div class="card " style="width: 18rem;">
                    <a href="/Detail?id=<%=productEntity.getID()%>">
                        <img src="../../img/<%=productEntity.getImagen()%>" class="card-img-top" alt="...">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title"><%=productEntity.getNombre()%></h5>
                        <%--<p class="card-text"><%=productEntity.getDescripcion()%></p>--%>
                        <%if(userEntity != null && !tipo.equals("Administrador") && productEntity.getInventario().getCantidad() > 0){%>
                            <a href="Basket/addProducto?id=<%=productEntity.getID()%>" class="btn btn-primary">Asignar productEntity al carrito</a>
                        <%}%>
                        <div class="fw-bold">Cantidad disponible: <%=productEntity.getInventario().getCantidad()%></div>
                        <%if(tipo.equals("Administrador")){%>
                            <div>
                                <a href="/eliminarProducto?id=<%=productEntity.getID()%>">eliminar Producto</a>
                            </div>
                            <div>
                                <a href="/modificarProducto?id=<%=productEntity.getID()%>">modificar Producto</a>
                            </div>
                        <%}%>
                        <a href="/Detail" class="btn btn-primary">Ver productEntity</a>
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
