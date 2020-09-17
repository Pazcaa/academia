package academia.controller;

import java.io.IOException;
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
 * Servlet implementation class InscribirCursoController
 */
@WebServlet("/inscribir-curso")
public class InscribirCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(InscribirCursoController.class);
	private static CursoDAOImpl daoCurso = CursoDAOImpl.getInstance();
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String mensaje = "";
		
		//recupero los parametros
		String pIdCurso = request.getParameter("idCurso");
		
		//recupero los datos de la sesion
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario_login");
		
		try {
			
			int idCurso = Integer.parseInt(pIdCurso);
			int idAlumno = usuario.getId();
			
			
			daoCurso.insertByAlumno(idAlumno, idCurso);
			
			mensaje = "Se ha inscrito con exito en el curso! ";
			
		} catch (Exception e) {
			LOG.error(e);
			mensaje = "No se ha podido inscribir en el curso seleccionado, por favor vuelva intentarlo";
			
		}finally {
			
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("panel-alumno").forward(request, response);
			
		}
	
	}


}
