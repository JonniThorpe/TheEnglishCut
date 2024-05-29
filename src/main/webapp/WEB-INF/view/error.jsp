
<%
    String error = (String) request.getAttribute("error");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error en Eventos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">Error!</h4>
        <%if(error == null){%>
            <p>Ha ocurrido un error en uno de los eventos. Por favor, intentalo de nuevo mas tarde.</p>
        <%}else {%>
            <p><%=error%></p>
        <%}%>
        <hr>
        <p class="mb-0">Si el problema persiste, contacta con el soporte tecnico.</p>
    </div>
</div>
</body>
</html>