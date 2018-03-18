package viewimage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.FormMultiPart;
import domain.Image;
import domain.Statistic_file;
import domain.User;
import exceptions.DomainException;
import exceptions.ServiceException;
import meta.FileMetadata;
import services.ServiceImage;
import services.ServiceStatistic_file;
import util.Fecha;

/**
 * Servlet implementation class ViewImage
 */
public class ViewImage extends HttpServlet {
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
		int numfilesubidos=0;
		FormMultiPart  datos=null;
		HttpSession session=request.getSession();
		
		String path=getServletContext().getRealPath(getServletContext().getInitParameter("dirViewImage"))+"/"+session.getId();
		try{	
			File file=new File(path);
			if (!file.exists())
				file.mkdirs();
			
			// si queremmos coger la ruta final del parametro del fichero web.xml 
			try {
				datos= new FormMultiPart(path,request);
				 numfilesubidos=datos.SubirFicheros();
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(),e);
			} 
			int timeExpired=Integer.parseInt(datos.getFieldForm("viewImage"));
			
			String folder = datos.getFieldFile("imageFile");
			if(folder.isEmpty())
				throw new ServiceException("You have not selected any file");
			String [] namefile=folder.split("\\\\");
				
//			System.out.println("Numero de fich subidos  "+numfilesubidos );
//			System.out.println("Ruta del fichero subido "+ datos.getFieldFile("imageFile"));
//			System.out.println("Ruta del fichero fichero  "+ folder);				
//			System.out.println(namefile[namefile.length-1]);

			String relativePath=getServletContext().getInitParameter("dirViewImage")+"/"+session.getId()+"/"+namefile[namefile.length-1];
			String url_redirect=String.valueOf(UUID.randomUUID());
			Image image=new Image(url_redirect,relativePath , new User("0","anonymous"), Fecha.fechaActual(), Fecha.sumarMesesFecha(Fecha.fechaActual(), timeExpired));
			
			ServiceImage sImage=new ServiceImage();
			try{
				sImage.createImage(image);
			}catch(SQLException e){
				image=sImage.recoverImageForUrl(image.getUrl_image());
				
			}
				
			FileMetadata fm=new FileMetadata(folder);
			Statistic_file statistic_file=new Statistic_file("ViewImage", fm.readExtensionFile(), Fecha.fechaActual());
			ServiceStatistic_file sStatistic=new ServiceStatistic_file();
			
			sStatistic.create(statistic_file);
			
			request.setAttribute("image", url_redirect);
			salida="/successfullyCompleted_ViewImage.jsp";
				
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				salida="/viewImage.jsp";	//Error Logic
			}else{
				e.printStackTrace();
				salida="/errorInternal.jsp";//Internal error
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			salida="/viewImage.jsp";		//Error Logic
			
		}

		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}	// THE END


}
