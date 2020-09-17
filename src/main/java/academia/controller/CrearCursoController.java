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
 * Servlet implementation class CrearCursoController
 */
@WebServlet("/crear-curso")
public class CrearCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CrearCursoController.class);
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
		
		Curso curso = new Curso();
		String mensaje = "";
		
		//recupero los parametros
		String pCurso = request.getParameter("curso");
		String pIdentificador = request.getParameter("identificador");
		String pHoras = request.getParameter("horas");
		
		//recupero los datos de la sesion
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario_login");
		
		try {
			
			int horas = Integer.parseInt(pHoras);
			//int idProfesor = usuario.getId();
			
			curso.setNombre(pCurso);
			curso.setIdentificador(pIdentificador);
			curso.setHoras(horas);
			curso.setProfesor(usuario);
			
			daoCurso.insert(curso);
			
			mensaje = "Su nuevo curso ha sido agregado con exito";
			
		} catch (Exception e) {
			LOG.error(e);
			
			mensaje = "No se ha podido añadir su nuevo curso, por favor vuelva intentarlo";
			
		}finally {
			
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("panel-profesor").forward(request, response);
			
		}
	
	}

}
