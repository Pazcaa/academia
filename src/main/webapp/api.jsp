<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="api" />
 	 <jsp:param name="title" value="Api" /> 
</jsp:include>

		<h1>API WS REST</h1>
		
		<h2>Cursos</h2>
		<p>Endpoint: <b>http://localhost:8080/academia/api/profesor/</b></p>
		<p>Podemos realizar un CRUD para los cursos de un profesor:</p>
		
		
		<p>Metodos habilitados:</p>
		
		<ul class="list-group">
		
			<li class="list-group-item">
				<span class="badge badge-success">GET</span> 
				<b>endpoint/curso/</b>   
				<span class="float-right badge badge-secondary ml-2">200</span>
				<span class="float-right">Listado de todos los cursos de todos los profesores</span>
			</li>
			<li class="list-group-item">
				<span class="badge badge-success">GET</span> 
				<b>endpoint/curso/{id}</b>   
				<span class="float-right badge badge-secondary ml-2">200</span>
				<span class="float-right badge badge-secondary ml-2">204</span>
				<span class="float-right">Detalle de cursos de un profesor</span>
			</li>
		
			<li class="list-group-item">
				<span class="badge badge-danger">DELETE</span> 
				<b>endpoint/curso/{id}</b>   
				<span class="float-right badge badge-secondary ml-2">200</span>
				<span class="float-right badge badge-secondary ml-2">409</span>
				<span class="float-right">Eliminar un curso de un profesor</span>
			</li>
		
			<li class="list-group-item">
				<span class="badge badge-warning">PUT</span> 
				<b>endpoint/curso/{id}</b>   
				<span class="float-right badge badge-secondary ml-2">200</span>
				<span class="float-right badge badge-secondary ml-2">409</span>
				<span class="float-right">Modificar un curso de un profesor</span>
			</li>
		
			<li class="list-group-item">
				<span class="badge badge-info">POST</span> 
				<b>endpoint/curso/</b>   
				<span class="float-right badge badge-secondary ml-2">201</span>
				<span class="float-right badge badge-secondary ml-2">409</span>
				<span class="float-right">Crear un curso para un profesor</span>
			</li>
		
		
		
		</ul>
		
		<p>Los metodos POST y PUT deben enviar datos en el body con formato JSON, los datos obligatorios tienen asterisco</p>
		
		<code><pre>
		
	    {
	        "id": 1,
	        <b>"*nombre"</b>: "Microsoft Office 2016",
	        <b>"*identificador"</b>: "I001",
	        <b>"*horas"</b>: 50,
	        "profesor": {
	            <b>"*id"</b>: 1,
	            "nombre": "Alain",
	            "apellidos": "Moles",
	            "rol": 2,
	            "password": ""
	        },
	        "numAlumnos": 2
	    }

		</pre></code>











<jsp:include page="includes/footer.jsp"></jsp:include>