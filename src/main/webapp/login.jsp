<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 15/03/2024
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap/css-js.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="text-center w-25 p-3">
    <form >
        <div class="grid gap-3">
            <div class="p-2 g-col-6">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping-1">User</span>
                    <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping">
                </div>
            </div>
            <div class="p-2 g-col-6">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping-2">Password</span>
                    <input type="text" class="form-control" placeholder="Password" aria-label="Username" aria-describedby="addon-wrapping">
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-primary btn-lg">Enviar</button>
    </form>
</div>
<a href="home.jsp" class="btn btn-primary">Volver</a>
</body>
</html>
