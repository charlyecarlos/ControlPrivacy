package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.ServiceUser;
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
		ServiceUser suser=new ServiceUser();
		HttpSession session=request.getSession(false);
		if(session!=null)
			session.invalidate();
		session=request.getSession();
		User user;
		try {
			user=new User(request.getParameter("email"));
			user=suser.recoverComplete(user);
			if (user!=null)
				if (user.getLocked().equals("S")){
					request.setAttribute("user", user);
					output="desbloquearCuenta.jsp";
				}else
					if (validateUser(user, suser, request)){
						session=request.getSession();
						session.setAttribute("user", user);
						switch (user.getType().getDescription()){
							case "Administrator":output="admin.html";break;
							case "User":output="user.html";break;
						}
					}else
						throw new DomainException("The password is wrong.");
			else if(request.getParameter("email").isEmpty())
				throw new ServiceException("Email cannot be empty.");
			else
				throw new ServiceException("The email does not exist.");
		} catch (ServiceException e) {
			if(e.getCause()==null){
				session.setAttribute("error",e.getMessage());
				output="login.html";
			}else{
				e.printStackTrace();
				output="errorInternal.jsp";
			}
		}catch (DomainException e) {
			session.setAttribute("error",e.getMessage());
			output="login.html";
		}
		response.sendRedirect(response.encodeRedirectURL(output));
	}
	
	private boolean validateUser(User user,ServiceUser suser,HttpServletRequest request) throws ServiceException {
		String password=EncryptMD5.encryptMD5(request.getParameter("password"));
		if(password.equals(user.getPassword()))
			return true;
		else{
			suser.incrementFail(user);
			return false;
		}
	}

}
