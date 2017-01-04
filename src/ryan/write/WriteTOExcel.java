package ryan.write;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import ryan.xml_to_xls.entity.MergeString;

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

    /**
     * 把 {@link ryan.xml_to_xls.entity.MergeString}对象写出到Excel中
     *
     * @param path     excel文件
     * @param entities 需要写出去的entity列表
     * @param page     写到第几个sheet中 0开头
     */
    public static void writeEntityToExcel(String path, List<MergeString> entities, int page) {
        File file = new File(path);
        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
            WritableSheet sheet = writableWorkbook.createSheet("sheet" + page, page);
            for (int i = 0; i < entities.size(); i++) {
                MergeString mergeString = entities.get(i);
                sheet.addCell(new Label(0, i, mergeString.key));
                sheet.addCell(new Label(1, i, mergeString.firstCountry));
                sheet.addCell(new Label(2, i, mergeString.secondCountry));
            }
            // 写入数据
            writableWorkbook.write();
            // 关闭文件
            writableWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }
}
