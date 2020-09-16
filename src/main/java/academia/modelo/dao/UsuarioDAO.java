package academia.modelo.dao;

import academia.modelo.pojo.Usuario;

public interface UsuarioDAO {


	/**
	 * Busca si existe el usuario en la base de datos
	 * @param nombre
	 * @param apellido
	 * @param password
	 * @return Usuario con los datos si estos existen, si no existe retorna null
	 */
	
	Usuario existe(String nombre, String apellido, String password);
}
