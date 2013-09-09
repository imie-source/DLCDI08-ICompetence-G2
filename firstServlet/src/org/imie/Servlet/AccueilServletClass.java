package org.imie.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class AccueilServletClass
 */
// en 3.0 on utilise les annotations pour le mapping

@WebServlet(description = "la premiere servlet", urlPatterns = { "/AccueilServletClass" })
public class AccueilServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		request.getRequestDispatcher("./Accueil.jsp").forward(request,
				response);
		

	}
}

	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
		
		response.setContentType("text/html");
		
		request.getRequestDispatcher("./Accueil.jsp").forward(request,
				response);
		