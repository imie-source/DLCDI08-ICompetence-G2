package org.imie.importExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
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
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;

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
        final String path = getServletContext().getRealPath("/upload");
        final String fileName = "test-import.xls";
        final String fichierXLS = path + File.separator + fileName;
        /**
         * A simple POI example of opening an Excel spreadsheet and writing its
         * contents to the command line.
         *
         * @author Tony Sintes
         */
        IUserService userService = BaseConcreteFactory.getInstance()
                .createUserService(null);

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
                    // Iterate over each cell in the row and print out the
                    // cell's
                    // content
                    Iterator cells = row.cellIterator();
                    while (cells.hasNext()) {
                        // Traitement de la date !!!! très simple !!!

                        if (HSSFDateUtil.isCellDateFormatted(row.getCell(3))) {

                            System.out.println("Row No.: "
                                    + row.getRowNum()
                                    + " "
                                    + DateFormat.getDateInstance(
                                            DateFormat.DEFAULT).format(
                                            row.getCell(2).getDateCellValue()));

                        }
                        HSSFCell cell = (HSSFCell) cells.next();
                        System.out.println("Cell #" + cell.getCellNum());
                        if (cell.getColumnIndex() == 2) {
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {

                                System.out.println(DateFormat.getDateInstance(
                                        DateFormat.DEFAULT).format(
                                        row.getCell(2).getDateCellValue()));

                            }
                        }
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println(cell.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            System.out.println(cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            break;
                        default:
                            System.out.println("unsuported sell type");
                            break;
                        }
                    }
                }

            }

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