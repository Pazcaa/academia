<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<jsp:include page="../includes/header.jsp">
	<jsp:param name="pagina" value="profesor" />
 	 <jsp:param name="title" value="Profesor" /> 
</jsp:include>


<h1>Bienvenido PROFESOR</h1>



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
		      <th scope="col">Operacion</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${cursos}" var="curso">
		    	<tr class="table-light">
			      <th scope="row">${curso.id}</th>
			      <td>${curso.nombre}</td>
			      <td>${curso.identificador}</td>
			      <td>${curso.horas}</td>
			      <td class="blockquote text-center"><a href="eliminar-curso?id=${curso.id}"><i class="fa fa-trash"></i></a></td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
		
	</div>	
	
	<div>
		<div>
			<button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapseExample">
			    Crear Nuevo Curso
			</button>
		</div>
		<div class="collapse" id="collapse1">
		
			<form action="crear-curso" method="post" >
			
				<div class="form-group">	
					<label for="nombre">Nombre del Curso </label>
					<input 	type="text" class="form-control"  name="curso" id="curso" value=" " 
										placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
				</div>	
				<div class="form-group">	
					<label for="nombre">Identificador </label>
					<input 	type="text" class="form-control"  name="identificador" id="identificador" value=" " 
										placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
				</div>	
				<div class="form-group">	
					<label for="nombre">Horas del Curso </label>
					<input 	type="text" class="form-control"  name="horas" id="horas" value=" " 
										placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
				</div>	
				
				<div class="form-group">
					<input type="submit" class="btn-outline-primary" name="crear_curso" value="Crear Curso">
				</div>
			
			</form>
		</div>
		
	
	</div>	

<jsp:include page="../includes/footer.jsp"></jsp:include>