<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	     <!-- datatables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
	
		<!--Nuestro CSS-->
	<link rel="stylesheet" href="css/style.css">
	
		<!--FAVICON-->
	<link rel="apple-touch-icon" sizes="57x57" href="imagenes/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="imagenes/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="imagenes/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="imagenes/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="imagenes/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="imagenes/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="imagenes/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="imagenes/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="imagenes/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="imagenes/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="imagenes/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="imagenes/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="imagenes/favicon/favicon-16x16.png">
	<link rel="manifest" href="imagenes/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	
	 <!-- Todas las rutas relativas comienzan por el href indicado -->    
        <base href="${pageContext.request.contextPath}/"/>
	
    <title>${param.title} |Academia</title>
  </head>
  <body>
  
  <header class="bg-dark shadow-sm">
	
	<div class="container d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3">
	  <h5 class="my-0 mr-md-auto font-weight-normal text-primary"><a href="index.jsp"><img alt="logo" src="imagenes/favicon/favicon-32x32.png"> ACADEMIA</a></h5>
	
 
	
	  <nav class="my-2 my-md-0 mr-md-3 navbar-dark">
	  
		<c:if test="${'2' eq usuario_login.rol }">
	    <a class="py-2 d-none d-md-inline-block  ${ ( 'profesor' eq param.pagina ) ? 'active' : '' }" href="panel-profesor">${usuario_login.nombre} ${usuario_login.apellidos}</a>
	   
		</c:if>
		
		<c:if test="${'1' eq usuario_login.rol }">
		<a class="py-2 d-none d-md-inline-block  ${ ( 'alumno' eq param.pagina ) ? 'active' : '' }" href="panel-alumno">${usuario_login.nombre} ${usuario_login.apellidos}</a>
		</c:if>
		
	  </nav>
	   	<c:if test="${empty usuario_login}">
	  		<a class="btn btn-outline-primary ${ ( 'login' eq param.pagina ) ? 'active' : '' }" href="login">Iniciar Sesion</a>
		</c:if>
		<c:if test="${not empty usuario_login}">
		 	<a class="btn btn-outline-primary" href="logout">Cerrar Sesion</a>
		</c:if>
	</div>
	
  </header>
  
  <main class="container">