package ryan;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ryanhuenwork on 16-9-29.
 */
public class ReplaceStringMain {
    /*
    other_chaozhuo_application
app_slogen
infos_browser_phone
infos_browser_pad
infos_filemanager
infos_text_editor
infos_phoenixos

     */
    public static void main(String[] args) {
        File file = new File("/home/ryanhuenwork/Documents/resources/encodeRigth");
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
