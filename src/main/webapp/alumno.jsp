<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="alumno" />
 	 <jsp:param name="title" value="Alumno" /> 
</jsp:include>


<h1>Bienvenido ALUMNO</h1>

${mensaje}

<jsp:include page="includes/footer.jsp"></jsp:include>