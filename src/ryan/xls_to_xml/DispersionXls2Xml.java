package ryan.xls_to_xml;

import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ryan.read.ReadFromExcel;
import ryan.utils.StringUtils;
import ryan.write.WriteToXML;

/**
 * Created by ryanhuencompany on 16-11-9.
 */
public class DispersionXls2Xml {
    /**
     * 从excel文件中读出数据以后，写入到xml文件中
     */

    /**
     * 指定读取xls文件的目录
     */
    public static final String STRING_XLS_FILE_FROM_PATH = "/home/ryanhuencompany/Desktop/Delivery_28L_20161108/";
    /**
     * 指定从项目中拷贝出来的res目录的位置
     */
    public static final String ANDROID_STRINGS_FILE_PATH = "/home/ryanhuencompany/Documents/explorer";
    public static final String[] LIST_NEED_TO_READ = {"browser_phone",
            "browser_pad",
            "filemanager",
            "text_editor",
            "phoenix_os"
    };

    public static void main(String[] args) {
        File androidStringRoot = new File(ANDROID_STRINGS_FILE_PATH);   //app的res目录
        listFileToSearchStringXML(androidStringRoot);   //从中查询出所有xml文件
        File file = new File(STRING_XLS_FILE_FROM_PATH);   //从哪里读取xls文件
        File[] files = file.listFiles();
        for (File excelFile : files) {
            //遍历xls，从中取得xls的名称，去掉后缀名（根据名称，可以匹配如：values-es目录，找到对应语言进行字符串添加）
            String fileName = getNameWithoutExtension(excelFile.getName());
            //读取xls中的内容
            ReadFromExcel readFromExcel = new ReadFromExcel(excelFile);
            Map<String, String> excelMaps = readFromExcel.readExcel(0, 2, 1, new ArrayList<String>(Arrays.asList(LIST_NEED_TO_READ)));
            //根据xls的名称和遍历来的strings.xml文件的路径进行对比，合适的语言就往里面写内容
            Set<String> keys = stringsFilePathList.keySet();
            for (String each : keys) {
                if (each.lastIndexOf(fileName) != -1 && each.lastIndexOf(fileName) != 4) {
                    try {
                        WriteToXML.appendToXML(stringsFilePathList.get(each), excelMaps);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static HashMap<String, String> stringsFilePathList = new HashMap<>();

    public static void listFileToSearchStringXML(File file) {
        File[] file1 = file.listFiles();
        for (File f : file1) {
            if (f.isDirectory()) {
                listFileToSearchStringXML(f);
            } else {
                if (f.getName().trim().equals("strings.xml")) {
                    stringsFilePathList.put(f.getParentFile().getName(), f.getAbsolutePath());
                }
            }

        }
    }

    public static String getNameWithoutExtension(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        final char dot = '.';
        final int pos = name.lastIndexOf(dot);
        if (pos == -1 || pos == 0) { // Hidden files doesn't have extensions
            return name;
        }

        // General extraction method
        return name.substring(0, pos);
    }
}
