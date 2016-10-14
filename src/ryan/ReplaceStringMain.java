package ryan;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ryanhuenwork on 16-9-29.
 */
public class ReplaceStringMain {

    /**
     * 从excel文件中读出数据以后，写入到xml文件中
     */
    public static final String STRING_XML_FILE_OUTPUT_PATH = "/home/ryanhuenwork/Documents/resources/encodeRigth";

    public static void main(String[] args) {
        File file = new File(STRING_XML_FILE_OUTPUT_PATH);
        File[] files = file.listFiles();
        for (File excelFile : files) {
            ReadFromExcel readFromExcel = new ReadFromExcel(excelFile);
            Map<String, String> excelMaps = readFromExcel.readExcel(0, 2, 1);
            try {
                WriteToXML.createXML2String(file.getAbsolutePath() + File.separator + excelFile.getName() + ".xml", excelMaps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
