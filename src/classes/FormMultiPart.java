/**
 * 
 */
package classes;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 * @author ANGEL (28/12/2014)
 * 
 *         / /** Gestiona la peticion de un formulario
 *         enctype=multipart/form-data incluido en un objeto request, es decir,
 *         el formulario incluye fields type=file
 */
public class FormMultiPart {
	private FileItem[] b;
	private File destino;

	/**
	 * 
	 * @param path
	 *            ruta donde vamos a almacenar los ficheros que se van a subir
	 *            al servidor
	 * @param request
	 *            objeto HttpServletRequest
	 * @throws FileUploadException
	 */
	public FormMultiPart(String path, HttpServletRequest request)throws FileUploadException {
		// por si no existe la ruta

		destino = new File(path);
		if (!destino.exists())
			destino.mkdir();
		ServletRequestContext src = new ServletRequestContext(request);
		// Si el formulario es enviado con Multipart
		if (ServletFileUpload.isMultipartContent(src)) {
			// Necesario para evitar errores de NullPointerException

			DiskFileItemFactory factory = new DiskFileItemFactory((1024 * 1024), destino);
			// Creamos un FileUpload
			ServletFileUpload upload = new ServletFileUpload(factory);
			// //Procesamos el request para que nos devuelva una lista
			// con los parametros y ficheros.

			List list = null;

			list = upload.parseRequest(src);

			b = new FileItem[list.size()];
			Iterator it = list.iterator();
			int cont = 0;// contador de ficheros subidos
			while (it.hasNext()) {
				// //Rescatamos el fileItem y los metemos en el array
				// eliminamos los file nulos

				b[cont] = (FileItem) it.next();

				cont = cont + 1;
			} // metemos en el array

		}

	} // fin del constructor

	/**
	 * 
	 * @param field
	 *            field de un formulario Multipart
	 * @return valor del field .null si no existe el field
	 */

	public String getFieldForm(String field) {
		int i = 0;
		while (i < b.length) {
			// Comprobamos si es un field de formulario

			if (b[i].isFormField() && b[i].getFieldName().compareTo(field) == 0)
				return b[i].getString();
			else
				i = i + 1;
		}

		return null;

	}

	/**
	 * 
	 * @param field
	 *            nombre de field que se repite en un formulario Multipart
	 * @return Valores de los fields. null si no existe el field
	 */
	public String[] getFieldFormValues(String field) {
		int i = 0, j = 0;
		// j para saber cuantos hay con el mismo nombre de field
		while (i < b.length) {
			// Comprobamos si es un field de formulario

			if (b[i].isFormField() && b[i].getFieldName().compareTo(field) == 0)
				j = j + 1;

			i = i + 1;
		}
		// ya sabemos cuantos hay recorremos nuevamente el array para enviarlos
		i = 0;
		if (j == 0)
			return null;
		else {
			i = 0;
			String datos[] = new String[j];
			j = 0;
			while (i < b.length) {
				// Comprobamos si es un field de formulario

				if (b[i].isFormField()
						&& b[i].getFieldName().compareTo(field) == 0) {
					datos[j] = b[i].getString();
					j = j + 1;
				}

				i = i + 1;
			}
			return datos;
		}

	}

	/**
	 * @return numero de ficheros subios al servidor
	 * @throws Exception
	 */
	public int SubirFicheros() throws Exception {
		int i = 0, j = 0;
		File file = null;
		while (i < b.length) {

			if (!b[i].isFormField()) {
				// si no tiene valor el field del fichero no subimos nada
				if (b[i].getName().compareTo("") != 0) {
					file = new File(b[i].getName());

					b[i].write(new File(destino, file.getName()));

					j = j + 1;
				}
			}
			i = i + 1;
		}

		return j;

	}

	/**
	 * 
	 * @param field
	 *            nombre del field tipe File de un formulario multipar
	 * @return ruta absoluta y fichero donde se subio el fichero. null si field
	 *         no existe
	 */
	public String getFieldFile(String field) {
		int i = 0;
		File file;
		while (i < b.length) {
			// Comprobamos si es un field de formulario

			if (!b[i].isFormField() && b[i].getFieldName().compareTo(field) == 0) {
				if (b[i].getName().compareTo("") != 0) {
					file = new File(b[i].getName());
					return destino + File.separator + file.getName();
				} else
					return "";

			} else
				i = i + 1;

		}
		return null;

	}

	/**
	 * 
	 * @param field
	 *            nombre del field tipe File que se repite en un formulario
	 *            multipar
	 * @return ruta absoluta y ficheros donde se subieron los ficheros. null si
	 *         el field no existe
	 */
	public String[] getFieldFileValues(String field) {
		int i = 0, j = 0;
		while (i < b.length) {
			// Comprobamos si es un field de formulario

			if (!b[i].isFormField()
					&& b[i].getFieldName().compareTo(field) == 0) {
				j = j + 1;
			}

			i = i + 1;
		}
		// ya sabemos cuantos ficheros se han subido j
		if (j == 0)

			return null;
		else {
			// los buscamos nuevamnete para meterlos en el array de retorno
			i = 0;
			String datos[] = new String[j];
			j = 0;
			File file;
			while (i < b.length) {
				// Comprobamos si es un field de formulario

				if (!b[i].isFormField()
						&& b[i].getFieldName().compareTo(field) == 0) {
					if (b[i].getName().compareTo("") != 0) {
						file = new File(b[i].getName());
						datos[j] = destino + File.separator + file.getName();

					} else
						datos[j] = "";
					j = j + 1;
				}
				i = i + 1;
			}
			return datos;
		}// fin del else
	}// fin del metodo
	
	public String getNameFile(String field){
		int i = 0;
		File file;
		while (i < b.length) {
			// Comprobamos si es un field de formulario

			if (!b[i].isFormField() && b[i].getFieldName().compareTo(field) == 0) {
				if (b[i].getName().compareTo("") != 0) {
					file = new File(b[i].getName());
					return file.getName();
				} else
					return "";

			} else
				i = i + 1;
		}
		return null;
	}
} // fin de la clase
