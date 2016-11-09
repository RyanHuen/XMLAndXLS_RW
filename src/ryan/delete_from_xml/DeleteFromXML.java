package ryan.delete_from_xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ryan.read.ReadFromXML;
import ryan.utils.StringUtils;
import ryan.write.WriteToXML;

/**
 * Created by ryanhuencompany on 16-11-9.
 * 从xml中删除字符串
 */
public class DeleteFromXML {

    /**
     * 从excel文件中读出数据以后，写入到xml文件中
     */
    public static final String ANDROID_STRINGS_FILE_PATH = "/home/ryanhuencompany/Documents/explorer/res";

    public static void main(String[] args) {
        //app的values目录集
        File androidStringRoot = new File(ANDROID_STRINGS_FILE_PATH);
        //从中查询出所有xml文件
        listFileToSearchStringXML(androidStringRoot);
        for (int i = 0; i < stringsFilePathList.size(); i++) {
            String s = stringsFilePathList.get(i);
            ReadFromXML readFromXML = new ReadFromXML(new File(s));
            Map<String, String> maps = readFromXML.getMaps();
            Set<String> keys = maps.keySet();
            List<String> deleteTmp = new ArrayList<>();
            for (String key : keys) {
                if (isTheKeyYouWantToDel(key)) {
                    deleteTmp.add(key);
                }
            }
            for (int j = 0; j < deleteTmp.size(); j++) {
                String s1 = deleteTmp.get(j);
                maps.remove(s1);
            }
            try {
                WriteToXML.createXML2String(new File(s).getAbsolutePath(), maps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isTheKeyYouWantToDel(String key) {
        String[] deleteKeys = {"browser_phone",
                "browser_pad",
                "filemanager",
                "text_editor",
                "phoenix_os"
        };
        List<String> deleteList = new ArrayList<>(Arrays.asList(deleteKeys));
        if (deleteList.contains(key)) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<String> stringsFilePathList = new ArrayList<>();

    public static void listFileToSearchStringXML(File file) {
        File[] file1 = file.listFiles();
        for (File f : file1) {
            if (f.isDirectory()) {
                listFileToSearchStringXML(f);
            } else {
                if (f.getName().trim().equals("strings.xml")) {
                    stringsFilePathList.add(f.getAbsolutePath());
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
