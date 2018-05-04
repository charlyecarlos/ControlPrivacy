package metadata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.DomainException;

import services.ServiceMetadata;

/**
 * Servlet implementation class deleteCleanMeta
 */
@WebServlet("/deleteCleanMeta")
public class deleteCleanMeta extends HttpServlet {
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
		
		String salida=null;
		HttpSession session=request.getSession();
		
		String urlFile = (String) session.getAttribute("urlFile");
		
		try{
			ServiceMetadata sMetadata=new ServiceMetadata();
			
			List<String> metadata=sMetadata.deleteMetadata(urlFile);
			request.setAttribute("Metadata", metadata);
			salida="/deleteMetadataFile.html";
		} catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			salida="/cleanMeta.jsp";		//Error Logic
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);	

	}

}
