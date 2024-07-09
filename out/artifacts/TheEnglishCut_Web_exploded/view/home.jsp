<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 13/03/2024
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Inicio</title>
    <!-- Referencias necesarias para usar bootstrap-->

</head>
<body>
<%@ include file="../../bootstrap/css-js.jsp"%>
<!--  Incluye el navbar de forma reusable-->
<%@ include file = "../componentes/Navbar.jsp" %>
<div id="carouselExampleFade" class="carousel slide carousel-fade">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="../../img/LogoProyecto.jpeg" class="d-block w-100" alt="..." style="height: 750px ; width: 500px; position: relative">
        </div>
        <div class="carousel-item">
            <img src="../../img/modern-sale-banner-website-slider-template-design_54925-46.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="../../img/indios.jpg" class="d-block w-100" alt="..." style="height: 750px ; width: 500px">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<div class="container mt-2">
    <div class="row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
