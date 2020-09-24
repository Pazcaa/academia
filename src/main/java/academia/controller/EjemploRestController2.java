package academia.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;

/**
 * Servlet implementation class EjemploRest2Controller
 */
@WebServlet("/ejemplo-rest2")
public class EjemploRestController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		int idUsuario = Integer.parseInt(request.getParameter("id"));
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));

		CursoDAOImpl dao = CursoDAOImpl.getInstance();

		try {
			Curso curso = dao.getById(idCurso, idUsuario);

			// respuesta para el body
			PrintWriter out = response.getWriter();

			Gson gson = new Gson();
			String stringBody = gson.toJson(curso);

			out.write( stringBody );

			out.flush();


			response.setStatus(HttpServletResponse.SC_OK);

		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}	


	}


}
