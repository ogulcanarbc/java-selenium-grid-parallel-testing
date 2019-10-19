package helper.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExcelHelper {

    private ExcelHelper() {

    }

    public static Workbook openWorkbook(InputStream inputStream) {
        return openWorkbook(inputStream, true);
    }

    public static Workbook openWorkbook(InputStream inputStream, boolean xlsx) {
        Workbook workbook = null;
        try {
            workbook = xlsx ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static void closeWorkbook(Workbook workbook) throws IOException {
        workbook.close();
    }

    public static Sheet getSheetByName(Workbook workbook, String sheetName) {
        return workbook.getSheet(sheetName);
    }

    public static boolean isEmptyRow(Row row) {
        return isEmptyCell(row.getCell(0));
    }

    public static boolean isEmptyCell(Cell cell) {
        return cell == null || (cell.getCellTypeEnum() == CellType.BLANK);
    }


    public static Object getCellValue(final Workbook workbook, final Cell cell) {
        Object cellValue = null;
        if (cell.getCellTypeEnum() == CellType.STRING) {
            cellValue = getCellValueAsString(cell);
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            cellValue = getNumericCellValue(cell);
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            cellValue = getCellValueAsBool(cell);
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            cellValue = evaluateCellFormula(workbook, cell);
        }
        return cellValue;
    }

    public static Object getNumericCellValue(final Cell cell) {
        Object cellValue;
        if (DateUtil.isCellDateFormatted(cell)) {
            cellValue = cell.getDateCellValue();
        } else {
            cellValue = cell.getNumericCellValue();
        }
        return cellValue;
    }

    public static Object evaluateCellFormula(final Workbook workbook, final Cell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper()
                .createFormulaEvaluator();
        CellValue cellValue = evaluator.evaluate(cell);
        Object result = null;

        if (cellValue.getCellTypeEnum() == CellType.BOOLEAN) {
            result = cellValue.getBooleanValue();
        } else if (cellValue.getCellTypeEnum() == CellType.NUMERIC) {
            result = cellValue.getNumberValue();
        } else if (cellValue.getCellTypeEnum() == CellType.STRING) {
            result = cellValue.getStringValue();
        }
        return result;
    }

    public static Cell getRowCell(Row row, int cellNum) {
        return row.getCell(cellNum);
    }

    public static Cell getRowCell(Row row, String cellNum) {
        return row.getCell(convertReferenceStringToInt(cellNum));
    }

    public static int convertReferenceStringToInt(String ref) {
        return CellReference.convertColStringToIndex(ref);
    }

    public static String convertReferenceIntToString(int ref) {
        return CellReference.convertNumToColString(ref);
    }

    public static String getRowCellValueAsString(Row row, String cellNum) {
        return getCellValueAsString(getRowCell(row, cellNum));
    }

    public static int getRowCellValueAsInt(Row row, String cellNum) {
        return getCellValueAsInt(getRowCell(row, cellNum));
    }

    public static boolean getRowCellValueAsBool(Row row, String cellNum) {
        return getCellValueAsBool(getRowCell(row, cellNum));
    }

    public static double getRowCellValueAsDouble(Row row, String cellNum) {
        return getCellValueAsDouble(getRowCell(row, cellNum));
    }

    public static float getRowCellValueAsFloat(Row row, String cellNum) {
        return getCellValueAsFloat(getRowCell(row, cellNum));
    }

    public static Date getRowCellValueAsDate(Row row, String cellNum) {
        return getCellValueAsDate(getRowCell(row, cellNum));
    }

    public static String getCellValueAsString(Cell cell) {
        String convertedStringValue = null;
        if (cell.getCellType() == CellType.STRING) {
            convertedStringValue = cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            convertedStringValue = String.valueOf(cell.getNumericCellValue()).replace(".0","").trim();
        }
        return isEmptyCell(cell) ? "" : convertedStringValue;
    }

    public static int getCellValueAsInt(Cell cell) {
        return isEmptyCell(cell) ? Integer.MIN_VALUE : (int) cell.getNumericCellValue();
    }

    public static boolean getCellValueAsBool(Cell cell) {
        return cell.getBooleanCellValue();
    }

    public static double getCellValueAsDouble(Cell cell) {
        return cell.getNumericCellValue();
    }

    public static float getCellValueAsFloat(Cell cell) {
        return (float) cell.getNumericCellValue();
    }

    public static Date getCellValueAsDate(Cell cell) {
        return cell.getDateCellValue();
    }

}
