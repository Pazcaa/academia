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
	
    <title>${param.title} |Academia</title>
  </head>
  <body>
  
  <header class="bg-dark shadow-sm">
	
	<div class="container d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3">
	  <h5 class="my-0 mr-md-auto font-weight-normal text-primary"><a href="index.jsp">ACADEMIA</a></h5>
	
 
	
	  <nav class="my-2 my-md-0 mr-md-3 navbar-dark">
	  
		<c:if test="${'2' eq usuario_login.rol }">
	    <a class="py-2 d-none d-md-inline-block  ${ ( 'profesor' eq param.pagina ) ? 'active' : '' }" href="profesor.jsp">${usuario_login.nombre} ${usuario_login.apellidos}</a>
	   
		</c:if>
		
		<c:if test="${'1' eq usuario_login.rol }">
		<a class="py-2 d-none d-md-inline-block  ${ ( 'alumno' eq param.pagina ) ? 'active' : '' }" href="alumno.jsp">${usuario_login.nombre} ${usuario_login.apellidos}</a>
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