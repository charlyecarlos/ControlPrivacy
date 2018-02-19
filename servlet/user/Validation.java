package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.ServiceTimeImage;

import domain.User;
import encrypt.EncryptMD5;

import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class Validation
 */
public class Validation extends HttpServlet {
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
		String output="";
		ServiceTimeImage suser=new ServiceTimeImage();
		User user;
		try {
			user=new User(request.getParameter("username"));
//			user=suser.recoverUserComplete(user);
			
			if (user.getLocked().equals("S")){
				request.setAttribute("user", user);
				output="desbloquearCuenta.jsp";
			}else
				if (validateUser(user, suser, request)){
					HttpSession sesion=request.getSession(false);
					if(sesion!=null)
						sesion.invalidate();
					sesion=request.getSession();
					sesion.setAttribute("user", user);
					switch (user.getType().getDescription()) {
						case "Administrator":output="admin.jsp";break;
						case "user":output="user.jsp";break;
					}
				}else
					throw new DomainException("The password is wrong.");
			
		} catch (ServiceException e) {
			if(e.getCause()==null)
				output="/index.jsp?error="+e.getMessage();
			else{
				e.printStackTrace();
				output="/errorinterno.jsp";
			}
	}catch (DomainException e) {
		output="/index.jsp?error="+e.getMessage();
	}
	request.getRequestDispatcher(output).forward(request, response);
	}
	
	private boolean validateUser(User user,ServiceTimeImage suser,HttpServletRequest request) throws ServiceException {
		String password=EncryptMD5.encryptMD5(request.getParameter("password"));
		if(password.equals(user.getPassword()))
			return true;
		else{
//			suser.incrementFail(user);
			return false;
		}
	}

}
