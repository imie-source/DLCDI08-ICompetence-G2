package org.imie.Securite;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.UserDTO;

/**
 * Servlet Filter implementation class FilterSecurity
 */
@WebFilter("/FilterSecurity")
public class FiltreSecurite implements Filter {

	private UserDTO userConnecte;
	private String authentification = null;
	private FilterConfig filterConfig = null;
	private String extensionNonFiltre = ".*(css|jpg|png|gif|js)";

	/**
	 * Default constructor.
	 */
	public FiltreSecurite() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// authentification = "non";
		System.out.println("mode :" + authentification);
		System.out
				.println("Passage dans le filtre >>>>>>------------------<<<<");
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String warning = "";

		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println("le chemin " + path);

		if (authentification.equalsIgnoreCase("oui")) {

			if (path.endsWith("Connexion") || path.endsWith("Authentification")
					|| path.matches(extensionNonFiltre)) {
				System.out.println(path);
				System.out.println("On demande la page connexion");

				chain.doFilter(request, response); // Just continue chain.
				//sreturn;

			}

			try {
				System.out.println("try");
				userConnecte = (UserDTO) session.getAttribute("user");
				if (userConnecte == null) {

					// l'utilisateur n'est pas authentifié correctement on le
					// redirige vers une page de login
					System.out.println("pas de user");
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("./Connexion.jsp");
					requestDispatcher.forward(request, response);

				} else {
					System.out.println(" Authentifié dans Filtre ");

					// l'utilisateur a été identifié il est autorisé à accéder à
					// la
					// servlet filtrée// chain.doFilter(request, response);

					chain.doFilter(req, res);
					return;

				}

			} catch (NullPointerException e) {
				System.out.println("catch");

				warning = "Premiere requete sur une page : pas de session ouverte avant";

			}

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.filterConfig = fConfig;
		authentification = filterConfig.getInitParameter("authentification");
		System.out.println(authentification);
		// TODO test sur la valeur
	}

}
