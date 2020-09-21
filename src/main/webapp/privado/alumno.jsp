<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="../includes/header.jsp">
	<jsp:param name="pagina" value="alumno" />
 	 <jsp:param name="title" value="Alumno" /> 
</jsp:include>

	<h1>Bienvenido ALUMNO</h1>
	
	
	<p>${mensaje}</p>

	<h2>Mis Cursos</h2>
	<div>
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
			      <td>${curso.profesor.nombre} ${curso.profesor.apellidos }</td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
		
	</div>	
	
	
		<div>
			<button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapseExample">
			    Inscribirme en un nuevo curso
			</button>
		</div>
		<div class="collapse" id="collapse1">

			<form action="inscribir-curso" method="post" >
			
				<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <button class="btn btn-outline-secondary" type="submit" name="#" >Seleccionar</button>
				  </div>
				  <select class="custom-select" name="idCurso" id="inputGroupSelect03" aria-label="Example select with button addon">
				    	<option selected>Elegir un curso</option>
				    <c:forEach items="${allCursos }" var="allCursos">
				   	 	<option value="${allCursos.id }">${allCursos.nombre }</option>
				    </c:forEach>
				  </select>
				</div>
			
			</form>
		</div>


<jsp:include page="../includes/footer.jsp"></jsp:include>