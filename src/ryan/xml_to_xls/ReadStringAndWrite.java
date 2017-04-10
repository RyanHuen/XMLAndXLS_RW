package ryan.xml_to_xls;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import ryan.read.ReadFromXML;
import ryan.write.WriteTOExcel;

/**
 * Created by ryanhuencompany on 16-11-2.
 */
public class ReadStringAndWrite {
    /**
     * 需要读取的字符串文件strings.xml
     */
    public static final String STRING_FILE_PATH_ZH_CN = "/home/ryanhuen/Documents/explorer/strings.xml";
    /**
     * 要把数据写入的excel文件所在的路径，不包括excel文件名称
     */
    public static final String OUTPUT_EXCEL_FILE_PATH = "/home/ryanhuen/Documents/explorer/test.xls";

    public static void main(String[] args) {
        ReadFromXML readFromXML = new ReadFromXML(new File(STRING_FILE_PATH_ZH_CN));
        Map<String, String> map = readFromXML.getMaps();
        WriteTOExcel.writeToExcelDouble(OUTPUT_EXCEL_FILE_PATH, new ArrayList<String>(map.keySet()), new ArrayList<String>(map.values()), 0);


    }
}
