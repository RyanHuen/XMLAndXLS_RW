package ryan.xml_to_xls;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ryan.read.ReadFromXML;
import ryan.write.WriteTOExcel;
import ryan.xml_to_xls.entity.MergeString;

/**
 * Created by ryanhuencompany on 17-1-4.
 * 把某两个语言的字符串根据key,把value分别写出到xls中
 */
public class MergeStringMain {
    /**
     * 第一个国家的strings.xml
     */
    public static final String STRING_FILE_PATH_FIRST_COUNTRY = "/home/ryanhuencompany/Documents/explorer/merge/strings_en.xml";
    public static final String STRING_FILE_PATH_SECOND_COUNTRY = "/home/ryanhuencompany/Documents/explorer/merge/strings_cn.xml";
    /**
     * 要把数据写入的excel文件所在的路径，不包括excel文件名称
     */
    public static final String OUTPUT_EXCEL_FILE_PATH = "/home/ryanhuencompany/Documents/explorer/merge/test.xls";
    public static final List<MergeString> MERGE_STRING_LIST = new ArrayList<>();

    public static void main(String[] args) {
        Map<String, String> firstMap = getCountryStrings(STRING_FILE_PATH_FIRST_COUNTRY);
        Map<String, String> secondMap = getCountryStrings(STRING_FILE_PATH_SECOND_COUNTRY);
        Set<String> keys = firstMap.keySet();
        for (String key : keys) {
            String first_c_String = firstMap.get(key);
            String second_c_String = secondMap.get(key);
            if (first_c_String != null && second_c_String != null) {
                MERGE_STRING_LIST.add(new MergeString(key, first_c_String, second_c_String));
            }
        }
        WriteTOExcel.writeEntityToExcel(OUTPUT_EXCEL_FILE_PATH, MERGE_STRING_LIST, 0);
    }

    private static Map<String, String> getCountryStrings(String path) {
        ReadFromXML readFromXML = new ReadFromXML(new File(path));
        return (Map<String, String>) readFromXML.getMaps();
    }
}
