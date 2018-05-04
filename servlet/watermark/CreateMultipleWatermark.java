package watermark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import classes.FormMultiPart;
import domain.Statistic_file;
import domain.User;
import exceptions.DomainException;
import exceptions.ServiceException;
import meta.FileMetadata;
import recursos.Position;
import services.ServiceStatistic_file;
import services.WaterMark;
import util.Fecha;

/**
 * Servlet implementation class CreateMultipleWatermark
 */
public class CreateMultipleWatermark extends HttpServlet {
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
        User user=(User) session.getAttribute("user");
		
		String path=getServletContext().getRealPath(getServletContext().getInitParameter("dirWatermark"))+"/"+session.getId();
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
				System.out.println(datos.getNameFile("position"));
				
				try{
					 numfilesubidos=datos.SubirFicheros();
				}catch (Exception e) {		
					throw new ServiceException(e.getMessage(),e);
				}			


			String [] folders = datos.getFieldFileValues("imageFiles");
			if(folders==null)
				throw new ServiceException("You have not selected any file");
			
//			System.out.println("Numero de fich subidos  "+numfilesubidos );
//			System.out.println("Ruta del fichero subido "+ datos.getFieldFile("imageFile"));
//			System.out.println("Ruta del fichero fichero  "+ folder);				
			FileMetadata fm;
			File image;
			Statistic_file statistic_file;
			ServiceStatistic_file sStatistic;
			
			String pathfile="dir/multiplefiles/"+user.getEmail()+"/"+UUID.randomUUID()+".zip";
			ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(new File(pathfile)));
			zip.setLevel(Deflater.DEFAULT_COMPRESSION);
			zip.setMethod(Deflater.DEFLATED);
			
			String position=datos.getFieldForm("position");
			System.out.println(position);
			
			ZipEntry entrada;
			FileInputStream fis;
			int read;
			for (String folder : folders) {
				fm=new FileMetadata(folder);
				image=new File(folder);

				switch(position){
				case "centered":
					WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.CENTERED);break;
				case "upper_left":
					WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.UPPER_LEFT);break;
				case "upper_right":
					WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.UPPER_RIGHT);break;
				case "delow_left":
					WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.DELOW_LEFT);break;
				case "delow_right":
					WaterMark.addTextWatermark(watermark, fm.readExtensionFile(), image, image,Position.DELOW_RIGHT);break;
				}

				entrada = new ZipEntry(image.getName());
				zip.putNextEntry(entrada);
				
				fis = new FileInputStream(folder);
				byte [] buffer = new byte[1024];
				read=0;
				while (0 < (read=fis.read(buffer))){
				   zip.write(buffer,0,read);
				}
				fis.close();
				zip.closeEntry();
				
				statistic_file=new Statistic_file("Watermark", fm.readExtensionFile(), Fecha.fechaActual());
				sStatistic=new ServiceStatistic_file();
				sStatistic.create(statistic_file);
			}
			zip.close();
			session.setAttribute("zip", pathfile);
			salida="/successfullyCompleted_MultipleWatermark.jsp";
		
		}catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				salida="/watermark-Picture.jsp";//Error Logic
			}else{
				e.printStackTrace();
				salida="/errorInternal.jsp?mensaje=Internal error";	//Internal error
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			salida="/watermark-Picture.jsp";//Error Logic	
		}
		
		getServletContext().getRequestDispatcher(salida).forward(request, response);
		
		}	// THE END

}
