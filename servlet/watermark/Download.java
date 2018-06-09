package watermark;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

/**
 * Servlet implementation class Download
 */

public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	final int BUFSIZE = 1024*8;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uploadPath=getServletContext().getRealPath(getServletContext().getInitParameter("dirWatermark"));
		HttpSession session=request.getSession();

        User user=(User) session.getAttribute("user");
		String filePath;
		if(request.getParameter("zip") != null)			
			filePath= uploadPath + File.separator+user.getEmail()+ File.separator + request.getParameter("zip").toString();
		else
			filePath= uploadPath + File.separator+session.getId() + File.separator + request.getParameter("image").toString();
		    
		File file = new File(filePath);
		    int length   = 0;
		    ServletOutputStream outStream = response.getOutputStream();
		    ServletContext context  = getServletConfig().getServletContext();
		    String mimetype = context.getMimeType(filePath);

		    // sets response content type
		    if (mimetype == null) {
		        mimetype = "application/octet-stream";
		    }
		    response.setContentType(mimetype);
		    response.setContentLength((int)file.length());
		    String fileName = (new File(filePath)).getName();

		    // sets HTTP header
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		    byte[] byteBuffer = new byte[BUFSIZE];
		    DataInputStream in = new DataInputStream(new FileInputStream(file));

		    // reads the file's bytes and writes them to the response stream
		    while ((in != null) && ((length = in.read(byteBuffer)) != -1))
		    {
		        outStream.write(byteBuffer,0,length);
		    }

		    in.close();
		    outStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int BUFSIZE = 1024*8;
		String filePath = request.getAttribute("image").toString();
		    File file = new File(filePath);
		    int length   = 0;
		    ServletOutputStream outStream = response.getOutputStream();
		    ServletContext context  = getServletConfig().getServletContext();
		    String mimetype = context.getMimeType(filePath);

		    // sets response content type
		    if (mimetype == null) {
		        mimetype = "application/octet-stream";
		    }
		    response.setContentType(mimetype);
		    response.setContentLength((int)file.length());
		    String fileName = (new File(filePath)).getName();

		    // sets HTTP header
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		    byte[] byteBuffer = new byte[BUFSIZE];
		    DataInputStream in = new DataInputStream(new FileInputStream(file));

		    // reads the file's bytes and writes them to the response stream
		    while ((in != null) && ((length = in.read(byteBuffer)) != -1))
		    {
		        outStream.write(byteBuffer,0,length);
		    }

		    in.close();
		    outStream.close();
	}

}
