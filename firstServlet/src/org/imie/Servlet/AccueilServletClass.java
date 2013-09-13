package org.imie.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccueilServletClass
 */
// en 3.0 on utilise les annotations pour le mapping

@WebServlet(description = "la premiere servlet",
			urlPatterns = { "/AccueilServletClass" },
			initParams ={@WebInitParam(name="profil",value="3")})
public class AccueilServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		System.out.println("Accueil");
		System.out.println(">>>>>>>Servlet name "+this.getServletContext().getServletContextName());

		

		request.getRequestDispatcher("./Accueil.jsp").forward(request,
				response);
	}
}
		