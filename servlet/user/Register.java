package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Type_User;
import domain.User;
import encrypt.EncryptMD5;
import exceptions.DomainException;
import exceptions.ServiceException;
import services.ServiceUser;
import util.Fecha;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=null;
		ServiceUser sUser=null;
		String output="";
		try{
			sUser=new ServiceUser();
			if (!request.getParameter("password").equals(""))
				if (request.getParameter("password").equals(request.getParameter("repeatPassword"))) {
					user=new User(request.getParameter("email"),request.getParameter("name"), request.getParameter("password"), new Type_User(2), Fecha.fechaActual(), Fecha.fechaActual());				
					user.setPassword(EncryptMD5.encryptMD5(user.getPassword()));
					sUser.create(user);
				}else
					throw new ServiceException("The passwords do not match .");
			else
				throw new ServiceException("The password cannot be empty.");
				
				output="login.html";
			} catch (ServiceException e) {
				if(e.getCause()==null){
					request.setAttribute("error",e.getMessage());
					output="/register.html";
				}else{
					e.printStackTrace();
					output="/errorinterno.jsp";
				}
		}catch (DomainException e) {
			request.setAttribute("error",e.getMessage());
			output="/register.html";
		}
		request.getRequestDispatcher(output).forward(request, response);	
	}

}
