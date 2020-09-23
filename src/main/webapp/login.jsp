<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="login" />
 	 <jsp:param name="title" value="Login" /> 
</jsp:include>


<span>${mensaje}</span>

<h1 class="text-center display-4 font-weight-normal">Iniciar Sesion</h1>

<div id="login">
	<form action="login" method="post">
	
	
		<div class="form-group">	
			<label for="nombre">Nombre </label>
			<input 	type="text" class="form-control"  name="nombre" id="nombre" value="${nombre}" 
								placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
		</div>	
		<div class="form-group">	
			<label for="nombre">Apellido </label>
			<input 	type="text" class="form-control"  name="apellido" id="apellido" value="${apellidos}" 
								placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
		</div>	
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" value="" placeholder="debe contener entre 4 y 10 caracteres" autofocus required>
		</div>	
		<div class="form-group">
			<input type="submit" class="btn-primary" name="login" value="Iniciar Sesion">
		</div>
	</form>
	
	
</div>

<jsp:include page="includes/footer.jsp"></jsp:include>