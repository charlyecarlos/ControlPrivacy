package metadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.FormMultiPart;
import domain.Metadata;
import domain.Statistic_file;
import exceptions.DomainException;
import exceptions.ServiceException;
import meta.FileMetadata;
import services.ServiceMetadata;
import services.ServiceStatistic_file;
import util.Fecha;

/**
 * Servlet implementation class Metadata
 */
public class CleanMeta extends HttpServlet {
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

        String exit=null;
		int numfilesubidos=0;
		FormMultiPart  datos=null;
		HttpSession session=request.getSession();
		
		String path=getServletContext().getRealPath(getServletContext().getInitParameter("dirFileAnalyse"))+"/"+session.getId();
		try{
			File file=new File(path);
			if (!file.exists())
				file.mkdirs();
			
			// si queremmos coger la ruta final del parametro del fichero web .xml 

			try {
				datos= new FormMultiPart(path,request);
				 numfilesubidos=datos.SubirFicheros();
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(),e);
			}
			
			String folder = datos.getFieldFile("localFile");
			if(folder.isEmpty())
				throw new ServiceException("You have not selected any file");
			String [] namefile=folder.split("\\\\");
				
//			System.out.println("Numero de fich subidos  "+numfilesubidos );
//			System.out.println("Ruta del fichero subido "+ datos.getFieldFile("imageFile"));
//			System.out.println("Ruta del fichero fichero  "+ folder);				
//			System.out.println(namefile[namefile.length-1]);

			String relativePath=getServletContext().getInitParameter("dirFileAnalyse")+"/"+session.getId()+"/"+namefile[namefile.length-1];
			
			ServiceMetadata sMetadata=new ServiceMetadata();
			
			List<Metadata> metadata=sMetadata.readMetadata(folder);
			
			Statistic_file statistic_file=new Statistic_file("FileAnalyse", metadata.get(0).toString(), Fecha.fechaActual());
			ServiceStatistic_file sStatistic=new ServiceStatistic_file();
			sStatistic.create(statistic_file);

			session.setAttribute("urlFile", relativePath);
			request.setAttribute("Metadata", metadata);
			exit="/statisticsFile.html";
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				exit="/cleanMeta.jsp";	//Error Logic
			}else{
				e.printStackTrace();
				exit="/errorInternal.jsp";//Internal error
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			exit="/cleanMeta.jsp";		//Error Logic
		}
		getServletContext().getRequestDispatcher(exit).forward(request, response);	
	}

}
