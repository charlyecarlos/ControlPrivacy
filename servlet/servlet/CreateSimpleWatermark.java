package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.IOUtils;

import classes.FormMultiPart;
import exceptions.DomainException;
import exceptions.ServiceException;
import meta.FileMetadata;
import services.WaterMark;

/**
 * Servlet implementation class CreateWatermark
 */
@WebServlet("/CreateSimpleWatermark")
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

        String salida=null;
		int numfilesubidos=0; 
		FormMultiPart  datos=null;
		try{	
			File file=new File(getServletContext().getRealPath(getServletContext().getInitParameter("dirUploadFiles")));
			if (!file.exists())
				file.mkdirs();
			
			
			// si queremmos coger la ruta final del parametro del fichero web.xml 
			String  path = getServletContext().getRealPath(getServletContext().getInitParameter("dirUploadFiles"));
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
//				System.out.println("valor del fichero  "+ watermark);
//				System.out.println("Ruta del fichero fichero  "+ folder);
				
				FileMetadata fm=new FileMetadata(folder);
				File image=new File(folder);
				WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image);

				
//				// Convert image to array byte
//				FileInputStream myStream = new FileInputStream(folder);
//				byte[] imageData=IOUtils.toByteArray(myStream);
//				
//				// display image 
//				response.setContentType("application/download");	//+fm.readExtensionFile());
//				response.getOutputStream().write(imageData);

				
				//	DOWNLOAD IMAGE
//				File file=new File((String) request.getAttribute("image"));
//			    response.setContentType("application/octet-stream");
//			    response.setHeader("Content-Disposition",
//			            "attachment;filename="+image.getName());
//			    FileInputStream fileIn = new FileInputStream(image);
//			    ServletOutputStream out = response.getOutputStream(); 
//			    byte[] outputByte = new byte[(int)image.length()];
//			    //copy binary contect to output stream
//			    while(fileIn.read(outputByte, 0, (int)image.length()) != -1){
//			    response.getOutputStream().write(outputByte, 0, (int) file.length());
//			    out.write(outputByte, 0, (int)image.length());
//			    }
//			    fileIn.close();
			


				
				request.setAttribute("image", file.getName()+"/"+image.getName());
//				request.setAttribute("image", folder);
				
				salida="/successfullyCompleted.jsp";
				
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				salida="/watermark-Picture.jsp";//Error Logic
			}else{
				e.printStackTrace();
				salida="/ErorInterno.jsp?mensaje=Internal error";	//Internal error		SIN TERMINAR
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			salida="/watermark-Picture.jsp";//Error Logic
			
		}

	getServletContext().getRequestDispatcher(salida).forward(request, response);

	}	// THE END

}
