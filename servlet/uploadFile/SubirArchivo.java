package uploadFile;
import java.io.*;

import classes.FormMultiPart;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileUploadException;

import exceptions.DomainException;
import exceptions.ServiceException;
public class SubirArchivo extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doPost  (HttpServletRequest	request,HttpServletResponse	response) throws ServletException, IOException {

        String salida=null;
		int numfilesubidos=0; 
		FormMultiPart  datos=null;
	try{	
		File file=new File("/dir");
		if (!file.exists())
			file.mkdirs();
		
		// añadimos al getRealPath la ruta / ó /ficheros
		//	String path = getServletContext().getRealPath("/ficheros");
		// si queremmos coger la ruta final del parametro del fichero web.xml 
		String  path = getServletContext().getRealPath(getServletContext().getInitParameter("dirUploadFiles"));
		//String  path ="d:/angel";// si queremos subir el fichero fuera del contexto
		System.out.println("valor del PARAMETRO cod_cli  "+ request.getParameter("cod_cli"));
		try {
			datos   = new FormMultiPart(path,request);
		} catch (FileUploadException e) {
			throw new ServiceException(e.getMessage(),e);

		} 
			String resultado[]= datos.getFieldFormValues("localFile");
			int i=0;
			while(i<resultado.length){
				System.out.println("valor del fichero  "+ resultado[i]);
				i=i+1;
			}

			try{
				 numfilesubidos=datos.SubirFicheros();
			}catch (Exception e) {	
				
				throw new ServiceException(e.getMessage(),e);
			}
			System.out.println("Numero de fich subidos  "+numfilesubidos );
			System.out.println("Ruta del fichero subido "+ datos.getFieldFile("fichero"));
			resultado = datos.getFieldFileValues("fichero1");
			i=0;
			while(i<resultado.length){
				System.out.println("Ruta del fichero fichero1  "+ resultado[i]);
				i=i+1; 
			}
			String cadena="¡¡¡ QUE GRANDE!!!!"+"se han subido "+numfilesubidos +"   ficheros";
			salida="/MensajeOk.jsp?mensaje="+cadena	;
			request.setAttribute("cadena", " oe oe ooooe ¡¡¡PentaCampeones!!!");
	} catch (ServiceException e) {
		if(e.getCause()==null){
			salida="/ErrorLogico.html?mensaje="+e.getMessage()	;//Error Lógico
		}else{
			e.printStackTrace();
			salida="/ErorInterno.jsp?mensaje=Error interno";//Error interno
		}
	}catch (DomainException e) {
		salida="/ErrorLogico.html?mensaje="+e.getMessage()	;//Error Lógico
		
	}

    getServletContext().getRequestDispatcher(salida).forward(request, response);

	}	// fin del post
}