package watermark;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import classes.FormMultiPart;
import domain.Statistic_file;
import exceptions.DomainException;
import exceptions.ServiceException;
import meta.FileMetadata;
import resources.Position;
import services.ServiceStatistic_file;
import services.WaterMark;
import util.Fecha;

/**
 * Servlet implementation class CreateWatermark
 */
public class CreateSimpleWatermark extends HttpServlet {
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

        String output=null;
		int numfilesubidos=0; 
		FormMultiPart  datos=null;
		HttpSession session=request.getSession();
		String path=getServletContext().getRealPath(getServletContext().getInitParameter("dirWatermark"))+File.separator+session.getId();
		try{	
			File file=new File(path);
			if (!file.exists())
				file.mkdirs();
			
			// si queremmos coger la ruta final del parametro del fichero web.xml 
			try {
				datos= new FormMultiPart(path,request);
			} catch (FileUploadException e) {
				throw new ServiceException(e.getMessage(),e);
			} 
				String watermark= datos.getFieldForm("textWatermark");
				if(watermark.isEmpty())
					throw new ServiceException("The watermark can´t be empty");
				
				try{
					 numfilesubidos=datos.SubirFicheros();
				}catch (Exception e) {		
					throw new ServiceException(e.getMessage(),e);
				}			
				
				String folder = datos.getFieldFile("imageFile");
				if(folder.isEmpty())
					throw new ServiceException("You have not selected any file");
				
//				System.out.println("Numero de fich subidos  "+numfilesubidos );
//				System.out.println("Ruta del fichero subido "+ datos.getFieldFile("imageFile"));
//				System.out.println("Ruta del fichero fichero  "+ folder);				
				
				FileMetadata fm=new FileMetadata(folder);
				File image=new File(folder);
				WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.CENTERED);
				
				
				Statistic_file statistic_file=new Statistic_file("Watermark", fm.readExtensionFile(), Fecha.fechaActual());
				ServiceStatistic_file sStatistic=new ServiceStatistic_file();
				sStatistic.create(statistic_file);
			
				
				//request.setAttribute("image", path+File.separator+image.getName());
				session.setAttribute("image", image.getName());
				output="successfullyCompleted_SimpleWatermark.html";
				
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				output="watermark-Picture.html";//Error Logic
			}else{
				e.printStackTrace();
				output="errorInternal.html?mensaje=Internal error";	//Internal error
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			output="watermark-Picture.html";//Error Logic
			
		}

		response.sendRedirect(response.encodeRedirectURL(output));

	}	// THE END

}
