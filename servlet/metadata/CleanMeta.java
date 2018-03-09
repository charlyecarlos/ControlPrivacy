package metadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import services.ServiceStatistic_file;
import util.Fecha;

/**
 * Servlet implementation class Metadata
 */
@WebServlet("/CleanMeta")
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

        String salida=null;
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
			System.out.println(folder);
			if(folder.isEmpty())
				throw new ServiceException("You have not selected any file");
			String [] namefile=folder.split("\\\\");
				
//			System.out.println("Numero de fich subidos  "+numfilesubidos );
//			System.out.println("Ruta del fichero subido "+ datos.getFieldFile("imageFile"));
//			System.out.println("Ruta del fichero fichero  "+ folder);				
//			System.out.println(namefile[namefile.length-1]);

			String relativePath=getServletContext().getInitParameter("dirFileAnalyse")+"/"+session.getId()+"/"+namefile[namefile.length-1];

			FileMetadata fm=new FileMetadata(folder);
			
			List<Metadata> metadata=new ArrayList<Metadata>();
			
			//	Generic Metadata
			metadata.add(new Metadata("Extension", fm.readExtensionFile(), false));
			metadata.add(new Metadata("Last Modified", fm.readLastModified().toString(), true));
			
			
			Statistic_file statistic_file=new Statistic_file("ViewImage", fm.readExtensionFile(), Fecha.fechaActual());
			ServiceStatistic_file sStatistic=new ServiceStatistic_file();
			sStatistic.create(statistic_file);

			request.setAttribute("Metadata", metadata);
			salida="/statisticsFile.jsp";
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("error", e.getMessage());
				salida="/cleanMeta.jsp";	//Error Logic
			}else{
				e.printStackTrace();
				salida="/errorInternal.jsp";//Internal error
			}
		}catch (DomainException e) {
			request.setAttribute("error", e.getMessage());
			salida="/cleanMeta.jsp";		//Error Logic
		}
		System.out.println(salida);
		getServletContext().getRequestDispatcher(salida).forward(request, response);	
	}

}
