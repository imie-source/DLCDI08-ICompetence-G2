package org.imie.importExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.IUserService;


/**
 * Servlet implementation class ImportExcel
 */
@WebServlet("/ImportExcelClass")
public class ImportExcelClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportExcelClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		HttpSession session = request.getSession();
		session.setAttribute("listeuser", userDTOs);
		request.getRequestDispatcher("./importUser.jsp").forward(
				request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		IUserService userService = BaseConcreteFactory
				.getInstance().createUserService(null);
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		HttpSession session = request.getSession();
		System.out.println("ok post");
		userDTOs = (ArrayList<UserDTO>) session.getAttribute("listeuser");
		System.out.println(userDTOs);
		
	}

}
