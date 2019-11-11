import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

class excelData extends excelCreater{

    static void result() {
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i);
        }

        while (true) {
            try (OutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                break;

            } catch (Exception e) {
                System.out.println("creating excel fail!!");
                System.out.println("Please run the program again.");
                System.out.println("Program terminated.");
                System.exit(0);
            }
        }

    }

    static void addData(String[] data){
        try {
            FileInputStream file = new FileInputStream(fileName);

            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
            Row row = sheet.createRow(Integer.parseInt(data[0]));

            for(int i=0; i < data.length; i++){
                row.createCell(i).setCellValue(data[i]);
            }
            result();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}