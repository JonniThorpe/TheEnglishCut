<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.theenglishcut.entity.UserEntity" %>


<%
    UserEntity userEntity = (UserEntity) session.getAttribute("user");
    String tipo = "Usuario";
    if(userEntity != null){

        tipo = userEntity.getRol().getNombre();
    }

    List<String> listCategoria = (List<String>) session.getAttribute("listaCategoriasNombres");
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">The English Cut</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/" >Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/listadoProductos?Categoria=TODO" >Productos</a>
                </li>
                <%if(userEntity != null){%>
                <li class="nav-item">
                    <a class="nav-link" href="Orders/listarPedidos" >Pedidos</a>
                </li>
                <%}%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"  role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Catálogo
                    </a>
                    <ul class="dropdown-menu">
                        <%if(listCategoria != null){ %>
                            <%for(String categoryEntity:listCategoria){%>
                                <li><a class="dropdown-item" href="/listadoProductos?Categoria=<%=categoryEntity%>"> <%=categoryEntity%> </a></li>
                                <li><hr class="dropdown-divider"></li>
                            <%}%>
                        <%}%>
                        <li><a class="dropdown-item" href="Category/createCategory"> Create Category </a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <div class="text-center">
                <div class="row align-items-center">
                    <div class="col">
                        <a class="align-middle" href="Basket/confirmarPedidoCliente">
                            <img src="../../iconos/carros.png"/>
                        </a>
                    </div>
                    <div class="col">
                        <%if(userEntity == null){%>
                        <div name="user" class="text-white"></div>
                        <%}else{%>
                        <img src="../../iconos/usuario.png"/>
                        <div name="user" class="text-white"><%=userEntity.getNombre()%></div>
                        <%}%>

                        <div name="tipo" class="text-white"><%=tipo%></div>

                    </div>
                    <div class="col">
                        <div class="container mt-3">
                            <div class="col">
                                <div class="row">
                                    <a name="login" class="btn btn-outline-success" href="/login" >Login</a>
                                </div>
                                <%if(userEntity == null || tipo.equals("Administrador")){%>
                                <div class="row">
                                    <a class="btn btn-outline-success" href="/register" >Register</a>
                                </div>
                                <%}else {%>
                                <div class="row">
                                    <a class="btn btn-outline-success" href="/logout">logout</a>
                                </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
