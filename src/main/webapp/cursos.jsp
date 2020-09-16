<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="cursos" />
 	 <jsp:param name="title" value="Cursos" /> 
</jsp:include>

<span>${mensaje } </span>

<h1> Listado de Cursos</h1>



		<table class="table table table-sm table-bordered table-info">
		  <thead>
		    <tr>
		      <th scope="col">id</th>
		      <th scope="col">Curso</th>
		      <th scope="col">Identificador</th>
		      <th scope="col">Horas</th>
		      <th scope="col">Profesor</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${cursos}" var="curso">
		    	<tr class="table-light">
			      <th scope="row">${curso.id}</th>
			      <td>${curso.nombre}</td>
			      <td>${curso.identificador}</td>
			      <td>${curso.horas}</td>
			      <td>${curso.profesor.nombre} ${curso.profesor.apellidos}</td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>




<jsp:include page="includes/footer.jsp"></jsp:include>