package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;

public interface CursoDAO {

	ArrayList<Curso> listar();
	
	/**
	 * Obtiene un listado de todos los cursos asociados a los profesores
	 * @param id_usuario
	 * @return
	 */
	
	ArrayList<Curso> cursosByProfesor (int id_usuario);
	
	/**
	 * Obtiene todos los cursos asociados a un alumno
	 * @param id_usuario
	 * @return
	 */
	
	ArrayList<Curso> cursosByAlumno (int id_usuario);
	
	Curso insert(Curso pojo) throws Exception;
	
	Curso delete (int idCurso, int idUsuario) throws Exception;
	
	Curso getById (int idCurso, int idUsuario) throws Exception;
	
	Curso insertByAlumno (int idUsuario, int idCurso) throws Exception;
	
	
	
}
