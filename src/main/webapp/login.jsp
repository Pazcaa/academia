
<p>${mensaje}</p>

<h1 class="text-center display-4 font-weight-normal">Iniciar Sesion</h1>

<div id="login">
	<form action="login" method="post">
	
		<div class="form-group">	
			<label for="nombre">Nombre </label>
			<input 	type="text" class="form-control"  name="nombre" id="nombre" value="${Usuario_login.nombre}" 
								placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
		</div>	
		<div class="form-group">	
			<label for="nombre">Apellido </label>
			<input 	type="text" class="form-control"  name="apellido" id="apellido" value="${Usuario_login.apellido}" 
								placeholder="Debe tener entre 3 y 100 caracteres" autofocus required>
		</div>	
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" value="${Usuario_login.password}" placeholder="debe contener entre 4 y 10 caracteres" required>
		</div>	
		<div class="form-group">
			<input type="submit" class="btn-primary" name="login" value="Iniciar Sesion">
		</div>
	</form>
	
	
</div>