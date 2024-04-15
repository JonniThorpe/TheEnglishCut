<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String usuario = (String)session.getAttribute("user");
    String tipo = (String)session.getAttribute("tipo");
    if(tipo == null){
        tipo = "usuario";
        usuario = "";
    }
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark border-bottom border-body" data-bs-theme="dark">>
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
                    <a class="nav-link" href="/listadoProductos" >Productos</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"  role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Catálogo
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Comida</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Ropa</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Muebles</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <div class="d-flex">
                <div class="m-xl-2">
                    <img src="../../iconos/usuario.png"/><br/>
                    <div class="text-white"><%=usuario%></div>
                    <div class="text-white"><%=tipo%></div>
                </div>
                <button class="btn btn-outline-success" type="submit" >
                    <a class="nav-link" href="/login" >Login</a>
                </button>
            </div>
        </div>
    </div>
</nav>
