package ryan;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ryan.write.WriteToXML;

/**
 * Created by ryanhuencompany on 16-11-9.
 */
public class CreateStringsXml {
    public static void main(String[] args) {
        File file = new File("/home/ryanhuencompany/Documents/explorer/res");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            if (file1.getName().startsWith("values") && !new File(file1.getAbsolutePath() + File.separator + "strings.xml").exists()) {
                try {
                    new File(file1.getAbsolutePath() + File.separator + "strings.xml").createNewFile();
                    Map<String, String> map = new HashMap<>();
                    WriteToXML.createXML2String(file1.getAbsolutePath() + File.separator + "strings.xml", map);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
