package org.imie.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.UserDTO;
import org.imie.IHM.Ecran;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class DeleteServletClass
 */
@WebServlet("/DeleteServletClass")
public class DeleteServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);
		
		UserDTO currentUserDTO = (UserDTO) session
				.getAttribute("userChoose");
		System.out.println(currentUserDTO);
		
		
		try {
			userService.deleteUser(currentUserDTO);
			
			
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		response.sendRedirect("./AccueilServletClass");
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
