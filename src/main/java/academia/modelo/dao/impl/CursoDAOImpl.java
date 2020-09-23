package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private final static String CONSULTA = "	SELECT \n" + 
											"	c.id 'curso_id', \n" + 
											"	c.curso 'curso_nombre',	\n" + 
											"	c.identificador 'identificador', \n" + 
											"	c.horas 'horas', \n" + 
											"	u.id 'usuario_id', \n" + 
											"	u.nombre 'usuario_nombre', \n" + 
											"	u.apellidos 'usuario_apellidos', \n" + 
											"	rol, \n" + 
											"	COUNT(ac.idAlumno) AS 'numero_alumnos' \n" +
											"	FROM usuarios u , cursos c\n" + 
											"	LEFT JOIN alumnosCurso ac \n" + 
											"	ON ac.idCurso = c.id \n" + 
											"	WHERE c.idProfesor = u.id 	 ";
	
	private final static String GROUP_ORDER_LIMIT = "	GROUP BY c.id \n" + 
													"	ORDER BY c.id ASC \n" + 
													"	LIMIT 500;\n";

	private final static String SQL_LISTAR = CONSULTA + GROUP_ORDER_LIMIT;
			
			/*
			 * " SELECT \n" + 
			 * "	c.id 'curso_id',\n" + 
											"	c.identificador 'identificador', \n" + 
											"	c.curso 'curso_nombre',\n" + 
											"	c.horas 'horas',\n" + 
											"	p.id 'usuario_id',\n" + 
											"	p.nombre 'usuario_nombre',\n" + 
											"	p.apellidos 'usuario_apellidos',\n" + 
											"	rol \n" + 
											"	FROM cursos c, usuarios p \n" + 
											"	WHERE c.idProfesor = p.id \n" +
											"   ORDER BY c.id ASC \n" + 
											" 	LIMIT 500 ;";
			 */
											
	
	private final static String SQL_CURSOS_PROFESOR = CONSULTA + "AND u.id = ? " + GROUP_ORDER_LIMIT; 
			
			/*" SELECT \n" + 
													"	c.id 'curso_id',\n" + 
													"	c.curso 'curso_nombre',\n" + 
													"	c.identificador 'identificador',\n" + 
													"	c.horas 'horas',\n" + 
													"	u.id 'usuario_id',\n" + 
													"	u.nombre 'usuario_nombre',\n" + 
													"	u.apellidos 'usuario_apellidos',\n" + 
													"	rol \n" + 
													"	FROM cursos c , usuarios u WHERE c.idProfesor = u.id AND u.id = ? \n" + 
													"   ORDER BY c.id ASC \n" +
													" 	LIMIT 500 ;";
													*/
	
	private final static String SQL_CURSOS_ALUMNO = CONSULTA + "AND ac.idAlumno = ?" + GROUP_ORDER_LIMIT;
			
			/*"	SELECT\n" + 
													"	c.id 'curso_id',\n" + 
													"	c.curso 'curso_nombre',\n" + 
													"	c.identificador 'identificador',\n" + 
													"	c.horas 'horas',\n" + 
													"	u.id 'usuario_id',\n" + 
													"	u.nombre 'usuario_nombre',\n" +
													"	u.apellidos 'usuario_apellidos',\n" +
													"	rol \n" + 
													"	FROM alumnosCurso ac , usuarios u , cursos c \n" + 
													"	WHERE ac.idCurso = c.id AND c.idProfesor = u.id AND ac.idAlumno = ? \n" +
													"   ORDER BY c.id ASC \n" + 
													" 	LIMIT 500 ;";
													
													*/
	
	private final static String SQL_CURSO_BY_ID = CONSULTA + " AND c.id =? AND u.id = ? " + GROUP_ORDER_LIMIT;
			
			/*"	SELECT\n" + 
												"	c.id 'curso_id', \n" + 
												"	c.curso 'curso_nombre',\n" + 
												"	c.identificador 'identificador',\n" + 
												"	c.horas 'horas',\n" + 
												"	u.id 'usuario_id',\n" + 
												"	u.nombre 'usuario_nombre',\n" +
												"	u.apellidos 'usuario_apellidos',\n" +
												"	rol \n" + 
												"	FROM cursos c, usuarios u  WHERE c.id =? AND u.id = ? \n " +
												"   ORDER BY c.id ASC \n" + 
												" 	LIMIT 500 ;";
												*/
	
	private final static String SQL_CURSOS_PROFESOR_NUM_ALUMNOS = CONSULTA + " AND u.id = ? " + GROUP_ORDER_LIMIT; 
			
			/*"	SELECT \n" + 
																"	c.id 'curso_id',\n" + 
																"	c.curso 'curso_nombre',\n" + 
																"	c.identificador 'identificador',\n" + 
																"	c.horas 'horas',\n" + 
																"	u.id 'usuario_id',\n" + 
																"	u.nombre 'usuario_nombre',\n" + 
																"	u.apellidos 'usuario_apellidos',\n" + 
																"	rol \n" + 
																"	FROM cursos c , usuarios u , alumnosCurso ac \n" + 
																"	WHERE c.idProfesor = u.id AND c.id = ac.idCurso AND u.id = ? \n" + 
																"	GROUP BY c.id \n" + 
																"	ORDER BY c.id ASC \n" + 
																"	LIMIT 500;";
	*/
	
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
				
				cursos.add(mappers(rs));
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
					
					cursos.add(mappers(rs));
					
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
					
					cursos.add(mappers(rs));
					
				}
			} 
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return cursos;
	}
	
	@Override
	public ArrayList<Curso> listarCursosconAlumnos(int id_usuario) {
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_CURSOS_PROFESOR_NUM_ALUMNOS);
				){
			
			pst.setInt(1, id_usuario);
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery();
					){
				while (rs.next()) {
					
					cursos.add(mappers(rs));
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
			
			ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					
					curso = mappers(rs);
					
				}else {
					throw new Exception();
					
				}
			
		
		}
		
		return curso;
	}

	@Override
	public Curso insert(Curso pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_CURSO, PreparedStatement.RETURN_GENERATED_KEYS);
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

	
	private Curso mappers(ResultSet rs) throws SQLException {
		
		Curso curso = new Curso();
		Usuario profesor = new Usuario();
		
		curso.setId(rs.getInt("curso_id"));
		curso.setNombre(rs.getString("curso_nombre"));
		curso.setIdentificador(rs.getString("identificador"));
		curso.setHoras(rs.getInt("horas"));
		curso.setNumAlumnos(rs.getInt("numero_alumnos"));
	
		profesor.setId(rs.getInt("usuario_id"));
		profesor.setNombre(rs.getString("usuario_nombre"));
		profesor.setApellidos(rs.getString("usuario_apellidos"));
		profesor.setRol(rs.getInt("rol"));
		
		
		curso.setProfesor(profesor);
	
		return curso;
	}
	

	



	

}
