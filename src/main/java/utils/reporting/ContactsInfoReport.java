package utils.reporting;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 30.03.2018
 */
public class ContactsInfoReport extends ExcelReportManager {
    private int rowStartNumber = 1;
    private int firstRowIndexOfRange = -1;
    private int lastRowIndexOfRange = -1;

    public enum Columns {
        NAME("Name", 0),
        LINK("Location", 1),
        COMPANY("Company", 2),
        LOCATION("Link", 3);

        private final String name;
        private final int number;

        Columns(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    @Override
    public String getReportName() {
        return "ContactsInfo";
    }

    @Override
    public Map<String, Integer> getColumnsMapFromEnum() {
        Map<String, Integer> columnsMap = new HashMap<>();
        for (Columns column : Columns.values()) {
            columnsMap.put(column.name, column.number);
        }
        return columnsMap;
    }

    public void savePersonInfo(Map<Columns, String> reportInfo) throws IOException {
        Row row = sheet.createRow(rowStartNumber);
        for (Columns column : reportInfo.keySet()) {
            row.createCell(column.number).setCellValue(reportInfo.get(column));
        }
        //Create Link type for last column with url-links
        createLinkTypeForLastColumn(row);

        addRowToRange(row);
        autoSizeColumns();
        rowStartNumber++;
        writeWorkbookToFile();
    }

    private void createLinkTypeForLastColumn(Row row) {
        CellStyle style = createHyperLinkCellStyle();
        Cell hyperLinkCell = row.getCell(Columns.LINK.number);
        String linkValue = hyperLinkCell.getStringCellValue();
        row.removeCell(hyperLinkCell);
        hyperLinkCell = row.createCell(Columns.LINK.number);
        Hyperlink link = getCreationHelper().createHyperlink(HyperlinkType.URL);
        link.setAddress(linkValue);

        hyperLinkCell.setCellStyle(style);
        hyperLinkCell.setHyperlink(link);
        hyperLinkCell.setCellValue(linkValue);
    }

    /**
     * Adds one more row in range
     */
    private void addRowToRange(Row row) {
        if (-1 == firstRowIndexOfRange) {
            firstRowIndexOfRange = row.getRowNum();
        }
        lastRowIndexOfRange = row.getRowNum();
    }
}
