package tech.ryanhuen.base;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import tech.ryanhuen.entity.MergeString;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    /**
     * 写出Map内容到excel文档
     *
     * @param path        excel文件
     * @param keys        map的键
     * @param values      map的值
     * @param targetSheet 写到第几个sheet中 0开头
     */
    public static void writeToExcelDouble(String path, List<String> keys, List<String> values, int targetSheet) {
        File file = new File(path);

        WritableWorkbook book = null;
        // 操作执行
        try {
            // file为要新建的文件名
            book = Workbook.createWorkbook(file);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet" + targetSheet, targetSheet);

            // 写入内容
            for (int i = 0; i < keys.size(); i++) {
                sheet.addCell(new Label(0, i, keys.get(i)));
                if (values != null && !values.isEmpty()) {
                    sheet.addCell(new Label(1, i, values.get(i)));
                }
            }
            // 写入数据
            book.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭文件
            if (book != null) {
                try {
                    book.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 单独写出Map中的key或者value到excel文档
     *
     * @param path        excel文件
     * @param values      需要写出去的map的key或者values
     * @param targetSheet 写到第几个sheet中 0开头
     */
    public static void writeToExcelSingle(String path, List<String> values, int targetSheet) {
        writeToExcelDouble(path, values, null, targetSheet);
    }

    /**
     * 把 {@link MergeString}对象写出到Excel中
     *
     * @param path        excel文件
     * @param entities    需要写出去的entity列表
     * @param targetSheet 写到第几个sheet中 0开头
     */
    public static void writeEntityToExcel(String path, List<MergeString> entities, int targetSheet) {
        File file = new File(path);
        WritableWorkbook writableWorkbook = null;
        try {
            writableWorkbook = Workbook.createWorkbook(file);
            WritableSheet sheet = writableWorkbook.createSheet("sheet" + targetSheet, targetSheet);
            for (int i = 0; i < entities.size(); i++) {
                MergeString mergeString = entities.get(i);
                sheet.addCell(new Label(0, i, mergeString.key));
                sheet.addCell(new Label(1, i, mergeString.firstCountry));
                sheet.addCell(new Label(2, i, mergeString.secondCountry));
            }
            // 写入数据
            writableWorkbook.write();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        } finally {
            // 关闭文件
            try {
                if (writableWorkbook != null) {
                    writableWorkbook.close();
                }
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }

    }
}
