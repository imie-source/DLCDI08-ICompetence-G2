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
	private Boolean premiereConnexion = true;
	private HttpSession session = null;

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

		System.out.println("mode :" + authentification);
		System.out
				.println("Passage dans le filtre >>>>>>------------------<<<<");
		session = ((HttpServletRequest) request).getSession(true);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String warning = "";

		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println("le chemin " + path);
		
		// On laisse toujours passer connexion, authentification, et css
				if (path.endsWith("Connexion") || path.endsWith("Authentification")
						|| path.matches(extensionNonFiltre)) {
					System.out.println(path);
					System.out.println(">>>>>>>>>>>>>>>>>>>>Pages non filtrées");

					chain.doFilter(request, response); // Just continue chain.
					return;

				}

		if (authentification.equalsIgnoreCase("oui")) {

			try {
				System.out.println("try");
				// userConnecte = (UserDTO) session.getAttribute("user");
				// if (userConnecte == null)
				if (!utilisateurAuthentifie()) {

					// l'utilisateur n'est pas authentifié correctement on le
					// redirige vers une page de login
					System.out.println("pas de user");
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("./Connexion.jsp");
					requestDispatcher.forward(request, response);
					

				} else {
					System.out.println(" Authentifié dans Filtre ");
					System.out.println(userConnecte.getNom());
					// l'utilisateur a été identifié il est autorisé à accéder à
					// la
					// servlet filtrée// chain.doFilter(request, response);
					if (premiereConnexion == true) {
						System.out
								.println(">>>>>>>>>>>>>>>>>>Authentifié / premeconnexion : "
										+ premiereConnexion);

						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("./Accueil.jsp");
						requestDispatcher.forward(request, response);
						premiereConnexion = false;
						
					} else {
						System.out
								.println(">>>>>>>>>>>>>>>>>>Authentifié / premeconnexion : "
										+ premiereConnexion);
						chain.doFilter(request, response);
						return;
					}

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
		System.out.println("valeur du parametre authentification : " +authentification);
		// TODO test sur la valeur
	}

	public boolean utilisateurAuthentifie() {
		boolean connecte = false;
		System.out.println("try");

		this.userConnecte = (UserDTO) session.getAttribute("user");
		if (userConnecte == null) {
			connecte = false;
		} else {
			connecte = true;
		}
		return connecte;
	}

	public String profilAuthentifie(UserDTO utilisateurAuthentifie) {
		return null;
	}
}
