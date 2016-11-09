
package ryan.read;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class ReadFromExcel {
    private Map<String, String> mapFromExcel = new HashMap<>();
    private File mFile;

    public ReadFromExcel(File file) {
        mFile = file;
    }

    /**
     * 读取Excel文件中的某个sheet，并转换成map集合
     *
     * @param page 读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param col1 读取哪一列的内容（一般指的是key所在列,从0开始）
     * @param col2 读取哪一列的内容（一般指的是values所在列，从0开始）
     * @return
     */
    public Map readExcel(int page, int col1, int col2) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2;
        try {
            // t.xls为要读取的excel文件名
            WorkbookSettings workbookSettings = new WorkbookSettings();
            book = Workbook.getWorkbook(mFile, workbookSettings);
            // 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(page);
            // // 获取左上角的单元格
            // cell1 = sheet.getCell(0, 1);
            // System.out.println("标题：" + cell1.getContents());

            i = 1;
            while (true) {
                // 获取每一行的单元格
                cell1 = sheet.getCell(col1, i);// （列，行）
                cell2 = sheet.getCell(col2, i);
                System.out.print(cell1.getContents() + "  " + cell2.getContents());
                // cell3 = sheet.getCell(2, i);
                if ("".equals(cell1.getContents())) // 如果读取的数据为空
                    break;
                mapFromExcel.put(cell1.getContents().trim(), cell2.getContents().trim());
                i++;
            }

            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keys = mapFromExcel.keySet();
        for (String key : keys) {
            System.out.print("excel  :  " + mapFromExcel.get(key));
            System.out.println("excel  :  " + key);
        }
        System.out.println();
        return mapFromExcel;
    }

    /**
     * 读取Excel文件中的某个sheet，并指定需要读取的内容，没指定的会自动略过，并转换成map集合
     *
     * @param page        读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param col1        读取哪一列的内容（一般指的是key所在列,从0开始）
     * @param col2        读取哪一列的内容（一般指的是values所在列，从0开始）
     * @param containKeys 传入需要读取出来的内容，否则全部掠过
     * @return
     */
    public Map readExcel(int page, int col1, int col2, List<String> containKeys) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2;
        try {
            // t.xls为要读取的excel文件名
            WorkbookSettings workbookSettings = new WorkbookSettings();
            book = Workbook.getWorkbook(mFile, workbookSettings);
            // 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(page);
            // // 获取左上角的单元格
            // cell1 = sheet.getCell(0, 1);
            // System.out.println("标题：" + cell1.getContents());

            i = 1;
            while (true) {
                // 获取每一行的单元格
                cell1 = sheet.getCell(col1, i);// （列，行）
                cell2 = sheet.getCell(col2, i);
                System.out.print(cell1.getContents() + "  " + cell2.getContents());
                // cell3 = sheet.getCell(2, i);
                if ("".equals(cell1.getContents())) {// 如果读取的数据为空
                    break;
                }
                if (containKeys.contains(cell1.getContents().trim())) {
                    mapFromExcel.put(cell1.getContents().trim(), cell2.getContents().trim());
                }
                i++;
            }

            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keys = mapFromExcel.keySet();
        for (String key : keys) {
            System.out.print("excel  :  " + mapFromExcel.get(key));
            System.out.println("excel  :  " + key);
        }
        System.out.println();
        return mapFromExcel;
    }
}
