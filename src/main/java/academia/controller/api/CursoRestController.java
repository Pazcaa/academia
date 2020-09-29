package academia.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;

/**
 * Servlet implementation class CursoRestController
 */
@WebServlet("/api/profesor/*")
public class CursoRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CursoRestController.class);  
	private static CursoDAOImpl dao = CursoDAOImpl.getInstance();
	private PrintWriter out = null;
	private int idProfesor;
	private String responseBody;
	private int statusCode;
   

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		LOG.debug("Se ejecuta SOLO la 1º vez que recibe una petición");		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		LOG.debug("Se ejecuta cuando se para la App");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Se ejecuta ANTES de GET, POST, PUT o DELETE");

		try {

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			/*
			//conseguir id de la URL si es que nos viene					
			idProfesor = 0;
			String pathInfo = request.getPathInfo();
			LOG.debug("url pathInfo:" + pathInfo );
			if ( pathInfo != null ) {
				String[] pathsParametros = pathInfo.split("/");
				if ( pathsParametros.length > 0 ) {
					idProfesor = Integer.parseInt(pathsParametros[1]);
				}
			}

*/
			
			responseBody = "{}";
			String pathInfo = request.getPathInfo();
			getIdFromPath(pathInfo); //llamo al metodo

			//out = response.getWriter();

			super.service(request, response);  // GET, POST, PUT o DELETE
			
			LOG.debug("Se ejecuta DESPUES de GET, POST, PUT o DELETE");

			//out.flush();

		}catch (Exception e) {

			e.printStackTrace();
			LOG.error(e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
			
		}	finally {
			
			//escribir respuesta
			out = response.getWriter();
			out.write(responseBody);
			response.setStatus(statusCode);
			out.flush();
		}



	}
	
	private void getIdFromPath (String pathInfo) {
		idProfesor = 0;
		
		LOG.debug("url pathInfo:" + pathInfo );
		
		if ( pathInfo != null ) {
			String[] pathsParametros = pathInfo.split("/");
			if ( pathsParametros.length > 0 ) {
				idProfesor = Integer.parseInt(pathsParametros[1]);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Listado
		if (idProfesor == 0) {
			
			ArrayList<Curso> cursos = dao.listar();

			Gson gson = new Gson();
			responseBody = gson.toJson(cursos);
			//out.write( stringBody );
			statusCode = HttpServletResponse.SC_OK;
			LOG.debug("GET: cursos recuperados " + cursos.size());

			
			
			//DETALLE
		}else {
			
			
			
			try {
				
				ArrayList<Curso> cursos = dao.cursosByProfesor(idProfesor);
				Gson gson = new Gson();
				responseBody = gson.toJson(cursos);
				//out.write( stringBody );
				LOG.debug("GET: cursos recuperados " + idProfesor);
				
				if (cursos.size() > 0) {
					statusCode = HttpServletResponse.SC_OK;
				}else {
					statusCode = HttpServletResponse.SC_NO_CONTENT;
				}
				
				
			} catch (Exception e) {
				
				statusCode = HttpServletResponse.SC_NO_CONTENT;
			}
		}//end else
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

}
