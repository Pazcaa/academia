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
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class EliminarCursoController
 */
@WebServlet("/eliminar-curso")
public class EliminarCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EliminarCursoController.class);
	private static CursoDAOImpl daoCurso = CursoDAOImpl.getInstance();
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String mensaje = " ";
		
		//recupero los parametros
		String pId = request.getParameter("id");
		
		//recupero los datos de la sesion
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario_login");
		
		try {
			
			int idCurso = Integer.parseInt(pId);
			
			daoCurso.delete(idCurso, usuario.getId());
			
			mensaje = "Su curso ha sido eliminado con exito";
			
		} catch (Exception e) {
			
			LOG.error(e);
			mensaje = "No se puede eliminar el curso, porfavor, vuelva a intentarlo";
			
		}finally {
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("panel-profesor").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	
	}

}
