
package tech.ryanhuen.base;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import java.io.File;
import java.util.*;

public class ReadFromExcel {
    public static final int NO_COL_TO_READ = -1;
    private File mFile;

    public ReadFromExcel(File file) {
        mFile = file;
    }

    /**
     * 读取Excel文件中的某个sheet，并转换成map集合
     *
     * @param sheetPage 读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param keyCol    读取哪一列的内容（指的是key所在列,从0开始）
     * @param valueCol  读取哪一列的内容（指的是values所在列，从0开始）
     * @return
     */
    public Map<String, String> readExcel(int sheetPage, int keyCol, int valueCol) {
        Map<String, String> contentMap = new HashMap<>();
        int i;
        Sheet sheet;
        Workbook book = null;
        Cell keyCell, valueCell = null;
        try {
            // t.xls为要读取的excel文件名
            WorkbookSettings workbookSettings = new WorkbookSettings();
            book = Workbook.getWorkbook(mFile, workbookSettings);
            // 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(sheetPage);
            // // 获取左上角的单元格
            // cell1 = sheet.getCell(0, 1);
            // System.out.println("标题：" + cell1.getContents());

            i = 1;
            while (true) {
                // 获取每一行的单元格
                keyCell = sheet.getCell(keyCol, i);// （列，行）
                if (valueCol != NO_COL_TO_READ) {
                    valueCell = sheet.getCell(valueCol, i);
                }
                if ("".equals(keyCell.getContents())) // 如果读取的数据为空
                    break;
                contentMap.put(keyCell.getContents().trim(), valueCell == null ? "" : valueCell.getContents().trim());
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                book.close();
            }
        }
        return contentMap;
    }

    /**
     * 读取Excel文件中的某个sheet，并指定需要读取的内容，没指定的会自动略过，并转换成map集合
     *
     * @param sheetPage  读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param keyCol     读取哪一列的内容（指的是key所在列,从0开始）
     * @param valueCol   读取哪一列的内容（指的是values所在列，从0开始）
     * @param targetKeys 需要从Excel中查找的key
     * @return
     */
    public Map<String, String> readExcel(int sheetPage, int keyCol, int valueCol, List<String> targetKeys) {
        Map<String, String> contentMaps = readExcel(sheetPage, keyCol, valueCol);
        if (contentMaps.isEmpty()) {
            return new HashMap<>();
        }
        Set<String> contentKeys = contentMaps.keySet();
        Map<String, String> targetMaps = new HashMap<>();
        for (int i = 0; i < targetKeys.size(); i++) {
            String key = targetKeys.get(i);
            if (contentKeys.contains(key)) {
                targetMaps.put(key, contentMaps.get(key));
            }
        }
        return targetMaps;
    }

    /**
     * 读取Excel文件中的某个sheet，并指定需要读取的内容，没指定的会自动略过，并转换成map集合
     *
     * @param sheetPage 读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param keyCol    读取哪一列的内容（指的是key所在列,从0开始）
     * @return
     */
    public Set<String> readExcel(int sheetPage, int keyCol) {
        Map<String, String> contentMaps = readExcel(sheetPage, keyCol, NO_COL_TO_READ);
        if (contentMaps.isEmpty()) {
            return new HashSet<>();
        }
        return contentMaps.keySet();

    }
}
