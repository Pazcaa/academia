package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;


public class CursoDAOImpl implements CursoDAO {

	private final static Logger LOG = Logger.getLogger(CursoDAOImpl.class);
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
	
	private final static String SQL_CURSOS_ALUMNO = "	SELECT\n" + 
													"	c.id 'id_curso',\n" + 
													"	c.curso 'nombre_curso',\n" + 
													"	c.identificador 'identificador',\n" + 
													"	c.horas 'horas',\n" + 
													"	u.nombre 'nombre_profesor',\n" +
													"	u.apellidos 'apellido_profesor'\n" +
													"	FROM alumnosCurso ac , usuarios u , cursos c \n" + 
													"	WHERE ac.idCurso = c.id AND c.idProfesor = u.id AND ac.idAlumno = ? ;";
	
	private final static String SQL_CURSO_BY_ID = "	SELECT\n" + 
												"	id 'id_curso', \n" + 
												"	curso 'nombre_curso',\n" + 
												"	identificador,\n" + 
												"	horas, \n" + 
												"	idProfesor \n" + 
												"	FROM cursos  WHERE id =? AND idProfesor = ?; ";
	
	private final static String SQL_INSERT_CURSO = "INSERT INTO cursos (curso, identificador, horas, idProfesor ) VALUES (?, ?, ?, ?);";
	
	private final static String SQL_INSERT_ALUMNO_CURSO = " INSERT INTO alumnosCurso (idAlumno, idCurso) VALUES (? , ?);";
	
	private final static String SQL_DELETE_CURSO = "DELETE FROM cursos WHERE id = ? AND idProfesor = ? ;";
	
	@Override
	public ArrayList<Curso> listar() {
		//System.out.println(SQL_LISTAR);
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try ( Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			ResultSet rs = pst.executeQuery()	){
			
			LOG.debug(pst);
			
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
			LOG.error(e);
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
			
			LOG.debug(pst);
			
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
			LOG.error(e);
		}
		
		return cursos;
	}
	
	@Override
	public ArrayList<Curso> cursosByAlumno(int id_usuario) {
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_CURSOS_ALUMNO);
				){
			
			pst.setInt(1, id_usuario);
			
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery();
					) {
				while (rs.next()) {
					
					Curso curso = new Curso();
					curso.setId(rs.getInt("id_curso"));
					curso.setNombre(rs.getString("nombre_curso"));
					curso.setIdentificador(rs.getString("identificador"));
					curso.setHoras(rs.getInt("horas"));
					
					Usuario p = new Usuario();
					p.setNombre(rs.getString("nombre_profesor"));
					p.setApellidos(rs.getString("apellido_profesor"));
					
					curso.setProfesor(p);
					
					cursos.add(curso);
					
				}
			} 
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return cursos;
	}
	
	@Override
	public Curso getById(int idCurso, int idUsuario) throws Exception {
		Curso curso = new Curso();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_CURSO_BY_ID);){
			
			pst.setInt(1, idCurso);
			pst.setInt(2, idUsuario);
			
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					
					curso.setId(rs.getInt("id_curso"));
					curso.setNombre(rs.getString("nombre_curso"));
					curso.setIdentificador(rs.getString("identificador"));
					curso.setHoras(rs.getInt("horas"));
					
					
					Usuario profesor = new Usuario();
					profesor.setId(rs.getInt("idProfesor"));
					
					curso.setProfesor(profesor);
					
				}else {
					throw new Exception();
					
				}
			
		}
		}
		
		return curso;
	}

	@Override
	public Curso insert(Curso pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_CURSO);
				) {
			
			pst.setString(1,pojo.getNombre());
			pst.setString(2, pojo.getIdentificador());
			pst.setInt(3, pojo.getHoras());
			pst.setInt(4, pojo.getProfesor().getId());
			
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				// conseguir el ID que nos ha arrojado
			
				try (ResultSet rskeys = pst.getGeneratedKeys()) {
					if (rskeys.next()) {
						int id = rskeys.getInt(1);
						pojo.setId(id);
					}
				}
			}else {
						throw new Exception("No se ha podido guardar el registro" + pojo);
					}

		} catch (Exception e) {
			LOG.error(e);
		}
		
		return pojo;
	}
	
	@Override
	public Curso insertByAlumno(int idUsuario, int idCurso) throws Exception {
		Curso curso = new Curso();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_ALUMNO_CURSO); ) {
			
			pst.setInt(1, idUsuario);
			pst.setInt(2, idCurso);
			
			pst.executeUpdate();
			
			LOG.debug(pst);
		} 
		
		return curso;
	}

	@Override
	public Curso delete(int idCurso, int idUsuario) throws Exception {
		
		Curso curso = getById(idCurso, idUsuario);
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE_CURSO); ){
			
			pst.setInt(1, idCurso);
			pst.setInt(2, idUsuario);
			
			pst.executeUpdate();
			
			LOG.debug(pst);
			
		} 
		
		return curso;
	}

	

	



	

}
