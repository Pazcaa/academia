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
	
	
	
}
