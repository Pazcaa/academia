package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class AlumnoController
 */
@WebServlet("/panel-alumno")
public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(AlumnoController.class);
	private static CursoDAOImpl daoCurso = CursoDAOImpl.getInstance();
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario_login");
		
		
		
		try {
			
			ArrayList<Curso> cursos = daoCurso.cursosByAlumno(usuario.getId());
			
			ArrayList<Curso> allCursos = daoCurso.listar();
			
			request.setAttribute("cursos", cursos);
			request.setAttribute("allCursos", allCursos);
			
		} catch (Exception e) {
			LOG.error(e);
			
		}finally {
			
			request.setAttribute("mensaje", usuario.getNombre() + " " + usuario.getApellidos() + " se ha conectado con exito");
			
			request.getRequestDispatcher("privado/alumno.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
