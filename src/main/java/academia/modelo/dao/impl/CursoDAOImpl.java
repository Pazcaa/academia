package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;


public class CursoDAOImpl implements CursoDAO {

	private static CursoDAOImpl INSTANCE = null; //patron singleton
	
	//constructor del INSTANCE
	public CursoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//metodo del INSTANCE
	public static synchronized CursoDAOImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CursoDAOImpl();
		}
		
		return INSTANCE;	
	}

	private final static String SQL_LISTAR = " SELECT \n" + 
											"	c.id 'curso_id',\n" + 
											"	c.identificador, \n" + 
											"	c.curso 'curso_nombre',\n" + 
											"	c.horas,\n" + 
											"	p.id 'profesor_id',\n" + 
											"	p.nombre 'profesor_nombre',\n" + 
											"	p.apellidos 'profesor_apellido',\n" + 
											"	rol \n" + 
											"	FROM cursos c, usuarios p \n" + 
											"	WHERE c.idProfesor = p.id ;";
	
	private final static String SQL_CURSOS_PROFESOR = " SELECT \n" + 
													"	c.id 'id_curso',\n" + 
													"	c.curso 'curso',\n" + 
													"	c.identificador 'identificador',\n" + 
													"	c.horas 'horas',\n" + 
													"	u.id 'id_profesor',\n" + 
													"	CONCAT(u.nombre, ' ', u.apellidos) as 'nombre_profesor'\n" + 
													"	FROM cursos c , usuarios u WHERE c.idProfesor = u.id AND u.id = ?;";
	
	@Override
	public ArrayList<Curso> listar() {
		System.out.println(SQL_LISTAR);
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try ( Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			ResultSet rs = pst.executeQuery()	){
			
			while (rs.next()) {
				Curso c = new Curso();
				c.setId(rs.getInt("curso_id"));
				c.setIdentificador(rs.getString("c.identificador"));
				c.setNombre(rs.getString("curso_nombre"));
				c.setHoras(rs.getInt("c.horas"));
				
				Usuario p = new Usuario();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				p.setApellidos(rs.getString("profesor_apellido"));
				p.setRol(rs.getInt("rol"));
				
				c.setProfesor(p);
				
				cursos.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos ;
	}



	@Override
	public ArrayList<Curso> cursosByProfesor(int id_usuario) {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_CURSOS_PROFESOR);
				){
			
			pst.setInt(1, id_usuario);
			
			try (ResultSet rs = pst.executeQuery();
					) {
				while (rs.next()) {
					
					Curso curso = new Curso();
					curso.setId(rs.getInt("id_curso"));
					curso.setNombre(rs.getString("curso"));
					curso.setIdentificador(rs.getString("identificador"));
					curso.setHoras(rs.getInt("horas"));
					
					cursos.add(curso);
					
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}

}
