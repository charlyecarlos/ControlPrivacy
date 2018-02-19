package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import exceptions.DomainException;
import exceptions.ServiceException;
import services.ServiceTimeImage;

/**
 * Servlet implementation class Desbloquear
 */
@WebServlet("/Desbloquear")
public class Unlock extends HttpServlet {
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
//		String salida="";
//		ServiceTimeImage sUser=new ServiceTimeImage();
//		User user=null;
//		try {
//			user=new User(request.getParameter("username"));
//			user=sUser.recoverUser(user);
//			if(user.getAnswer().equals(request.getParameter("resp")))
//				if(sUser.unlockedUser(user)==1)
//					salida="mensajes.jsp?titulo=User desbloqueado, recibiras un email con la nueva contraseña";
//				else
//					salida="/errorinterno.jsp";
//			else{
//				throw new ServiceException("La respuesta es incorrecta");
//			}
//		} catch (ServiceException e) {
//			if(e.getCause()==null){
//				request.setAttribute("user", user);
//				salida="/desbloquearCuenta.jsp?error="+e.getMessage();
//			}else{
//				e.printStackTrace();
//				salida="/errorinterno.jsp";
//			}
//		}catch (DomainException e) {
//			request.setAttribute("user", user);
//			salida="/desbloquearCuenta.jsp?error="+e.getMessage();
//		}
//		request.getRequestDispatcher(salida).forward(request, response);
	}

}
