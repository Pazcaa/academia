<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="inicio" />
 	 <jsp:param name="title" value="Inicio" /> 
</jsp:include>
 
	
			 <h1>ACADEMIA</h1>
			<% 
				//según carga la pagina principal, redireccionamos a un servidor
				response.sendRedirect( request.getContextPath() + "/cursos"); 
			%> 
			<p>estamos en index</p>
			<div>
				<a href="login" >Inicio Sesion </a>
			</div>
				
<jsp:include page="includes/footer.jsp"></jsp:include>
 




