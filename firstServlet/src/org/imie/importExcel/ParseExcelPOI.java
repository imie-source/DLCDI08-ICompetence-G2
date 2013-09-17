package org.imie.importExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DTO.AdresseDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class ParseExcelPOI
 */
@WebServlet(description = "Parse du fichier Excel", urlPatterns = { "/LectureExcel" })
public class ParseExcelPOI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParseExcelPOI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dans POI");
		response.setContentType("text/html;charset=UTF-8");
		final String path = getServletContext().getRealPath("/upload");
		final String fileName = "test-import.xls";
		final String fichierXLS = path + File.separator + fileName;
		final PrintWriter writer = response.getWriter();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		/**
		 * A simple POI example of opening an Excel spreadsheet and writing its
		 * contents to the command line.
		 * 
		 * @author Tony Sintes
		 */
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);

		UserDTO newUser = new UserDTO();
		AdresseDTO adresseToCreate = new AdresseDTO();
		IAdresseDAO adresseDAO = BaseConcreteFactory.getInstance()
				.createAdresseDAO(null);

		try {

			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fichierXLS));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();

				System.out.println("Row #" + row.getRowNum());
				// on passe la ligne d'entête
				if (row.getRowNum() > 0) {
					System.out.println(row.getCell(2).getDateCellValue());
					System.out.println(dateFormat.format(row.getCell(2)
							.getDateCellValue()));
					String chaineDate = dateFormat.format(row.getCell(2)
							.getDateCellValue());
					newUser.setNom(row.getCell(0).getStringCellValue());
					newUser.setPrenom(row.getCell(1).getStringCellValue());

					newUser.setDateNaiss(row.getCell(2).getDateCellValue());
					newUser.setAdresse_mail(row.getCell(3).getStringCellValue());
					newUser.setIdentifiant(row.getCell(4).getStringCellValue());
					newUser.setPwd(row.getCell(5).getStringCellValue());
					CursusDTO monCursus = new CursusDTO();
					monCursus.setId((int) row.getCell(6).getCellNum());
					newUser.setCursus(monCursus);
					// Gestion de la'adresse
					AdresseDTO nouvelleAdresse = new AdresseDTO();
					int codePostal = (int) row.createCell(9).getCellNum();

					String ville = row.createCell(8).getStringCellValue();
					String libelle = row.createCell(7).getStringCellValue();
					adresseToCreate.setLibelle(libelle);
					adresseToCreate.setVille(ville);
					adresseToCreate.setCode_postal(codePostal);
					try {
						nouvelleAdresse = adresseDAO
								.createAdresse(adresseToCreate);
					} catch (TransactionalConnectionException e) {
						e.printStackTrace();
					}

					newUser.setAdresse(nouvelleAdresse);

					try {
						System.out.println("CreationUser");
						userService.insertUser(newUser);
					} catch (TransactionalConnectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				}

			}
			writer.println("Le fichier  " + fileName + " a été importé en base de données");
			writer.println("</BR><a href=\"./ListeUserServlet\">Voir la liste à jour </a>");
			writer.println("</BR></BR> )");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}