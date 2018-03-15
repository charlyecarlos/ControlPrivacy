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
import services.Mail;
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
					user=new User(request.getParameter("name"),request.getParameter("email"), request.getParameter("password"), new Type_User(2), Fecha.fechaActual(), Fecha.fechaActual());				
					user.setPassword(EncryptMD5.encryptMD5(user.getPassword()));
					sUser.create(user);
					
					Mail mail=new Mail(getServletContext().getInitParameter("mail"), getServletContext().getInitParameter("passwordMail"), user.getEmail(),"Confirm account", mail());
					mail.sendMail();
				}else
					throw new ServiceException("The passwords do not match.");
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
	
	private String mail() {
		String mail="<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'><head><title>Activation</title></head><body style='text-align: center;'><h1>WELCOME TO 'CONTROL PRIVACY'</h1><h5>Press this button to activate your account.</h5><style>.btn {background: #3498db;background-image: -webkit-linear-gradient(top, #3498db, #2980b9);background-image: -moz-linear-gradient(top, #3498db, #2980b9);background-image: -ms-linear-gradient(top, #3498db, #2980b9);background-image: -o-linear-gradient(top, #3498db, #2980b9);background-image: linear-gradient(to bottom, #3498db, #2980b9);-webkit-border-radius: 28;-moz-border-radius: 28;border-radius: 28px;text-shadow: 1px 1px 3px #141214;font-family: Arial;color: #ffffff;font-size: 20px;padding: 10px 20px 10px 20px;text-decoration: none;}.btn:hover {background: #3cb0fd;background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);background-image: -o-linear-gradient(top, #3cb0fd, #3498db);background-image: linear-gradient(to bottom, #3cb0fd, #3498db);text-decoration: none;}</style><a href='activate' class='btn' role='button'>activate</a></body></html>";
		return mail;
	}

}
