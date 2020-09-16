<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="profesor" />
 	 <jsp:param name="title" value="Profesor" /> 
</jsp:include>


<h1>Bienvenido PROFESOR</h1>

${mensaje}

<p>${id}</p>

<jsp:include page="includes/footer.jsp"></jsp:include>