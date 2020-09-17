 // ejecuta la funcion cuando todo el documento de html DOM este listo y cargado
      
		$(document).ready(function() {
		    $('#table').DataTable();
		   
		$('[data-toggle="popover"]').popover()
		    	
		} );

      function confirmar(nombre) {
    		
    		// The confirm() method returns true if the user clicked "OK", and false otherwise. 
    		if ( confirm('¿ Estas seguro que quires eliminar ' + nombre + ' ?') ){
    			
    			console.debug(' continua el evento por defceto del ancla ');
    			
    		}else {
    			console.debug(' prevenimos o detenemos el evento del ancla ');
    			event.preventDefault();
    		}
    		
    	}