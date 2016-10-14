package ryan.write;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteTOExcel {
    /**
     * 写出Map内容到excel文档
     *
     * @param path   excel文件
     * @param keys   map的键
     * @param values map的值
     * @param page   写到第几个sheet中 0开头
     */
    public static void writeToExcelDouble(String path, List<String> keys, List<String> values, int page) {
        File file = new File(path);

        // 操作执行
        try {
            // file为要新建的文件名
            WritableWorkbook book = Workbook.createWorkbook(file);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet" + page, page);

            // 写入内容
            for (int i = 0; i < keys.size(); i++) {
                sheet.addCell(new Label(0, i, keys.get(i)));
                sheet.addCell(new Label(1, i, values.get(i)));
            }

            // 写入数据
            book.write();
            // 关闭文件
            book.close();
        } catch (Exception e) {
        }
    }

    /**
     * 单独写出Map中的key或者value到excel文档
     *
     * @param path   excel文件
     * @param values 需要写出去的map的key或者values
     * @param page   写到第几个sheet中 0开头
     */
    public static void writeToExcelSingle(String path, List<String> values, int page) {
        File file = new File(path);

        // 操作执行
        try {
            // file为要新建的文件名
            WritableWorkbook book = Workbook.createWorkbook(file);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet" + page, page);

            // 写入内容
            for (int i = 0; i < values.size(); i++) {
                sheet.addCell(new Label(0, i, values.get(i)));
            }

            // 写入数据
            book.write();
            // 关闭文件
            book.close();
        } catch (Exception e) {
        }
    }
}
