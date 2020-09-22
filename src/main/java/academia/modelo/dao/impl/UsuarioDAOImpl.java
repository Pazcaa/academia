package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.UsuarioDAO;
import academia.modelo.pojo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private final static Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);
	private static UsuarioDAOImpl INSTANCE = null; //patron singleton
	
	//constructor del INSTANCE
	public UsuarioDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//metodo del INSTANCE
	public static synchronized UsuarioDAOImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}
		
		return INSTANCE;	
	}

	// executeQUery => ResultSet
	private final static String SQL_EXISTE  = " SELECT u.id , u.nombre, u.apellidos, u.rol, u.password\n" + 
											" 	FROM usuarios u " +
											" 	WHERE nombre = ? AND apellidos = ? AND password = MD5(?) \n" +
											"   ORDER BY u.id ASC \n" + 
											" 	LIMIT 500 ;";
	

	@Override
	public Usuario existe(String nombre, String apellido,  String password) {
		Usuario usuario = null;
		
		try (Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_EXISTE);) {
			
			
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, password);
			
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery();){
				if (rs.next()) {
					usuario = new Usuario();
					usuario.setId(rs.getInt("u.id"));
					usuario.setNombre(rs.getString("u.nombre"));
					usuario.setApellidos(rs.getString("u.apellidos"));
					usuario.setRol(rs.getInt("u.rol"));
					usuario.setPassword(rs.getString("u.password"));
				}
			} 
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return usuario;
	}

}
