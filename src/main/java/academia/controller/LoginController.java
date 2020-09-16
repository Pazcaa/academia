package academia.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academia.modelo.dao.impl.UsuarioDAOImpl;
import academia.modelo.pojo.Usuario;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
 
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
		Usuario usuario = dao.existe(nombre, apellido, password);
		
		
		
		
		if (usuario != null) {
			
			session.setMaxInactiveInterval(60 * 5);//tras 5 min sin peticiones se desconecta automaticamente
			session.setAttribute("usuario_login", usuario);
			
			request.setAttribute("mensaje", usuario.getNombre() + " " + usuario.getApellidos() + " se ha conectado con exito");
			//request.getRequestDispatcher("inicio").forward(request, response);
			
			if (usuario.getRol() == usuario.ROL_ALUMNO) {
				
				request.getRequestDispatcher("alumno.jsp").forward(request, response);
				
			}else {
				request.getRequestDispatcher("profesor").forward(request, response);
			}
		
			
		}else {
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellidos", apellido);
			request.setAttribute("mensaje", "Sus datos son incorrectos, vuelva a intentarlo");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		
		
	}

}
