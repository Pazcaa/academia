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
import academia.modelo.dao.impl.UsuarioDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	private static UsuarioDAOImpl daoUsuario = UsuarioDAOImpl.getInstance();
	private static CursoDAOImpl daoCurso = CursoDAOImpl.getInstance();
			
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//parametros de entrada
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String password = request.getParameter("password");
		
		//Establezco session
		HttpSession session = request.getSession();
		
		//recupero el usuario
		Usuario usuario = daoUsuario.existe(nombre, apellido, password);
		
		try {
			
			if (usuario != null) {
				
				session.setMaxInactiveInterval(60 * 5);//tras 5 min sin peticiones se desconecta automaticamente
				session.setAttribute("usuario_login", usuario);
				
				request.setAttribute("mensaje", usuario.getNombre() + " " + usuario.getApellidos() + " se ha conectado con exito");
				//request.getRequestDispatcher("inicio").forward(request, response);
				
				if (usuario.getRol() == usuario.ROL_ALUMNO) {
					
					ArrayList<Curso> cursos = daoCurso.cursosByAlumno(usuario.getId());
					
					ArrayList<Curso> allCursos = daoCurso.listar();
					
					request.setAttribute("cursos", cursos);
					request.setAttribute("allCursos", allCursos);
					request.getRequestDispatcher("privado/alumno.jsp").forward(request, response);
					
				}else {
					
					
					ArrayList<Curso> cursos = daoCurso.cursosByProfesor(usuario.getId());
					
					request.setAttribute("cursos", cursos);
					request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
				}
			
				
			}else {
				request.setAttribute("nombre", nombre);
				request.setAttribute("apellidos", apellido);
				request.setAttribute("mensaje", "Sus datos son incorrectos, vuelva a intentarlo");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			LOG.error(e);
		}
	
	}

}
