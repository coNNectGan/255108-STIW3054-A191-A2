import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class excelCreater  {

    static XSSFWorkbook workbook;

    static XSSFSheet sheet;

    static String fileName = "ListOfFollowers.xlsx";

    static String sheetName = "ListOfFollowers";

    static String[] title = {"No", "Login ID", "Number Of Repositories", "Number Of Followers", "User ID", "User Type"};

    //Create a excelFile and format
    static void createExcel(){

        workbook = new XSSFWorkbook();

        workbook.createSheet(sheetName);

        sheet = workbook.getSheet(sheetName);

        Row row = sheet.createRow(0);


        for(int i=0; i < title.length; i++){
            row.createCell(i).setCellValue(title[i]);
        }

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for(int i = 0; i < title.length; i++){
            row.getCell(i).setCellStyle(style);
        }


        excelData.result();

    }

     //read the excel data
    static void readData() {

        try {
            FileInputStream file = new FileInputStream(fileName);

            XSSFWorkbook wb = new XSSFWorkbook(file);

            XSSFSheet sheet = wb.getSheet(sheetName);

            System.out.println("\n\n                                              List Of Followers");

            printLine();

            String format = "| %-3s| %-25s| %-25s| %-25s| %-10s| %-10s|\n";

            System.out.format(format,
                    sheet.getRow(0).getCell(0),
                    sheet.getRow(0).getCell(1),
                    sheet.getRow(0).getCell(2),
                    sheet.getRow(0).getCell(3),
                    sheet.getRow(0).getCell(4),
                    sheet.getRow(0).getCell(5));

            printLine();


            for (int i=1; i <= sheet.getLastRowNum(); i++ ) {

                Row row = sheet.getRow(i);

                ArrayList<String> rowData = new ArrayList<>();

                for (int j=0; j < row.getLastCellNum(); j++ ) {

                    Cell cell = row.getCell(j);

                    if (cell!=null) {

                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getRichStringCellValue().getString());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData.add(cell.getDateCellValue().toString());
                                } else {
                                    rowData.add(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            case BOOLEAN:
                                rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                rowData.add(cell.getCellFormula());
                                break;
                            default:
                                System.out.println();
                        }
                    }else {
                        rowData.add("");
                    }
                }

                System.out.format(format,rowData.toArray());
            }

            printLine();

        } catch (Exception ignored) { }
    }

    private static void printLine() {

        System.out.format("+%-3s+%-25s+%-25s+%-25s+%-10s+%-10s+\n",
                "----", "--------------------------", "--------------------------", "--------------------------", "-----------", "-----------");

    }

    //open excelFile
    static void excelfile(){

        File excelFile = new File(fileName);
        Desktop desktop = Desktop.getDesktop();

        try{
            desktop.open(excelFile);
        } catch (Exception e) {
            System.out.println(fileName +" cannot open!!");
        }
    }
}


