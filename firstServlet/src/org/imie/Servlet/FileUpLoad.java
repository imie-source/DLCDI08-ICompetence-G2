package org.imie.Servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet(name = "FileUpLoad", urlPatterns = { "/upload" })
@MultipartConfig
/**
 * 
 * @author ym
 *<p> D'après http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html</p>
 *@todo  vérifiaction du type de fichier
 */
public class FileUpLoad extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file
		
		final String path = getServletContext().getRealPath("/upload");
		
		final Part filePart = request.getPart("file");
		final String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();

		try {
			out = new FileOutputStream(new File(path + File.separator
					+ fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			writer.println("Le fichier  " + fileName + " a été importé dans le répertoire" + path);
			writer.println("</BR><a href=\"./LectureExcel\">Lancer l\'import en base </a>"); 
            writer.println("</BR></BR> ATTENTION L'OPERATION NE POURRA PAS ETRE ANNULEE !)");
		} catch (FileNotFoundException fne) {
			writer.println("Vous n'avez pas spécifié de fichier à importer ");
			writer.println("<br/> ERROR: " + fne.getMessage());

		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	
	
}
