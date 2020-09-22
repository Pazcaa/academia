package seguridad;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import academia.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class SeguridadFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/privado/*" })
public class SeguridadFilter implements Filter {
	
	private final static Logger LOG = Logger.getLogger(SeguridadFilter.class);


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("se destruye filtro");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			//Cuidado que hay que castear de ServletRequest -> HttpServletRequest
				HttpServletRequest req = (HttpServletRequest)request;
				HttpServletResponse res = (HttpServletResponse)response;
				
				//necesitamos la url de como comienza nuestra App, apra construir una ryta ABSOLUTA y que no sea relativa
				String urlInicioApp = req.getContextPath();
				
				LOG.trace("filtrando URI:" + req.getRequestURI() );
				
				// recuperar usuario de session
				Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_login");
				
				if (usuario == null) {
					LOG.warn("No ha pasado por el LOGIN, usuario NULL, sin autentificar.");
					res.sendRedirect(urlInicioApp + "/login"); // nos reenvia al login jsp (ruta absoluta)
					
				}else {
					// pass the request along the filter chain
					chain.doFilter(request, response);// esto nos redirige como profesor o alumno!
				}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("se inicializa filtro");
	}

}
