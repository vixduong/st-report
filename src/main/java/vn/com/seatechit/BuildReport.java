package vn.com.seatechit;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@Log4j2
public class BuildReport {
  public static void main(String[] args) throws IOException {
    try (
        InputStream is = new ClassPathResource("templates/grouping_template.xlsx").getInputStream();
        OutputStream out = Files.newOutputStream(Paths.get("target/object_collection_output.xlsx"));
        XSSFWorkbook reader = new XSSFWorkbook(is);
        XSSFWorkbook writer = new XSSFWorkbook()
    ) {
      XSSFSheet writerSheet = writer.createSheet();

      StreamSupport
          .stream(reader.getSheetAt(0).spliterator(), true)
          .flatMap(row -> StreamSupport.stream(row.spliterator(), false))
          .forEach(cell -> {
            XSSFCell wCell = writerSheet
                .createRow(cell.getRowIndex())
                .createCell(cell.getColumnIndex());
            wCell.getCellStyle().cloneStyleFrom(cell.getCellStyle());
            if (cell.getCellType() == CellType.STRING) {
              wCell.setCellValue(cell.getStringCellValue());
            } else if (cell.getCellType() == CellType.NUMERIC) {
              wCell.setCellValue(cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.BOOLEAN) {
              wCell.setCellValue(cell.getBooleanCellValue());
            } else if (cell.getCellType() == CellType.FORMULA && cell.getCachedFormulaResultType() == CellType.NUMERIC) {
              wCell.setCellValue(cell.getNumericCellValue());
            }
          });
      writer.write(out);
    }

    /*try (
        InputStream is = new ClassPathResource("templates/grouping_template.xlsx").getInputStream();
        OutputStream os = Files.newOutputStream(Paths.get("target/object_collection_output.xlsx"));
        ReadableWorkbook wb = new ReadableWorkbook(is, new ReadingOptions(true, true))
    ) {
      Workbook writeWb = new Workbook(os, "export", null);
      Worksheet ws = writeWb.newWorksheet("Data");

      Sheet sheet = wb.getFirstSheet();
      try (Stream<Row> rows = sheet.openStream()) {
        rows
            .parallel()
            .filter(r -> r.getCellCount() > 0)
            .forEach(r -> {
              final int rowNum = r.getRowNum();
              r.stream().map(Cell::getText).forEach(log::info);
              log.info(r);
            });
      }
    }*/

    /*try(InputStream is = new ClassPathResource("templates/formulas_template.xls").getInputStream()) {
      try (OutputStream os = Files.newOutputStream(Paths.get("target/object_collection_output.xlsx"))) {
        List<Employee> employees = generateSampleEmployeeData();
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "Nguyen Van A");
        map2.put("birthDate", LocalDate.now());
        map2.put("payment", new BigDecimal(100));
        map2.put("bonus", new BigDecimal(200));
        map.put("cell", "employees");
        map.put("header", "Hello World");
        map.put("employees", Collections.singletonList(map2));
        map.forEach(context::putVar);
//        JxlsHelper.getInstance().processTemplate(is, os, context);
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/
  }

  private static List<Employee> generateSampleEmployeeData() {
    return Arrays.asList(
        new Employee("John", new Date(), new BigDecimal("100"), new BigDecimal("10")),
        new Employee("Jane", new Date(), new BigDecimal("200"), new BigDecimal("20")),
        new Employee("Jack", new Date(), new BigDecimal("300"), new BigDecimal("30")),
        new Employee("Jill", new Date(), new BigDecimal("400"), new BigDecimal("40")),
        new Employee("Joe", new Date(), new BigDecimal("500"), new BigDecimal("50"))
    );
  }
}
