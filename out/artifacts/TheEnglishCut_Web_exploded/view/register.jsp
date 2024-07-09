<%@ page import="com.theenglishcut.entity.RolEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../bootstrap/css-js.jsp"%>
<%

    String tipo = (String)session.getAttribute("tipo");
    if(tipo == null){
        tipo = "Usuario";
    }
    List<RolEntity> roles = (List<RolEntity>) request.getAttribute("roles");
%>

<html>
<head>
    <title>Registrar nuevo usuarios</title>
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            position: relative;
            overflow: hidden;
        }
        body::before, body::after {
            content: '';
            position: absolute;
            top: 0;
            width: 50%;
            height: 100%;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            z-index: -1;
        }
        body::before {
            left: 0;
            background-image: url('../../img/gobesponja.gif');
        }
        body::after {
            right: 0;
            background-image: url('../../img/gobesponja.gif');
            transform: scaleX(-1); /* Voltea horizontalmente la segunda imagen */
        }

    </style>
</head>
<body>

<div class=" mt-2 d-flex justify-content-center" >
    <form action="/registerUser" method="post">
        <h1>REGISTRO USUARIO</h1>
        <div class="grid gap-3">
            <div class="p-2 g-col-6">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping-1">User</span>
                    <input name="nombre" type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping"/>
                </div>
            </div>
            <div class="p-2 g-col-6">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping-2">Password</span>
                    <input name="password" type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="addon-wrapping">
                </div>
            </div>
            <%if(tipo.equals("Administrador")){%>
                <%for(RolEntity rol:roles){%>
                <input name="rolId" type="radio" id="rol" value="<%=rol.getID()%>">
                <label for="rol"><%=rol.getNombre()%></label>
                <%}%>
            <%}else{%>
            <input hidden name="rolId" value="<%=2%>">
            <%}%>
            <div class="p-2 g-col-6">
                <div class="input-group flex-nowrap">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </div>
            </div>
        </div>
        <a href="/" class="btn btn-primary">Volver</a>
    </form>
    <br/>
</div>
</body>
</html>
