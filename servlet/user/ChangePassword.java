package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import encrypt.Encrypt;
import exceptions.DomainException;
import exceptions.ServiceException;
import services.ServiceUser;

/**
 * Servlet implementation class ChangePassword
 */

public class ChangePassword extends HttpServlet {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String newPassword = request.getParameter("newPassword");
		
		ServiceUser suser=new ServiceUser();
		String output = "";
		
		try {
			if (validateUser(user, suser, request.getParameter("lastPassword")))
				if(newPassword.equals(request.getParameter("repeatNewPassword"))){
					user.setPassword(Encrypt.encryptSHA256(newPassword));
					if(suser.update(user)!=0){
						session.setAttribute("notify","Changed password successfully");
						output="settings.html";
					}else
						throw new DomainException("A problem has occurred, again intentardo in a few minutes ");
						
				}else
					throw new DomainException("The password is wrong.");
		} catch (ServiceException e) {
			if(e.getCause()==null){
				session.setAttribute("error",e.getMessage());
				output="settings.html";
			}else{
				e.printStackTrace();
				output="errorInternal.jsp";
			}
		}catch (DomainException e) {
			session.setAttribute("error",e.getMessage());
			output="settings.html";
		}
		response.sendRedirect(response.encodeRedirectURL(output));
	}

	private boolean validateUser(User user,ServiceUser suser,String password) throws ServiceException {
		password=Encrypt.encryptSHA256(password);
		if(password.equals(user.getPassword())){
			user.setAccessfail(0);
			suser.update(user);
			return true;
		}else{
			suser.incrementFail(user);
			return false;
		}
	}
}
