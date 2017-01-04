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
     * 指定res目录，遍历其内部的strings.xml进行删除字符串
     */
    public static final String ANDROID_STRINGS_FILE_PATH = "/home/ryanhuencompany/Documents/explorer/merge/";

    public static final String[] DELETE_KEYS = {  "phone_sort_size_s_l",
            "installed",
            "add_to_desktop_shorcut",
            "recent_file_loading_tips",
            "phone_sort_size_l_s",
            "phone_sort_date_new_old",
            "content_loading",
            "phone_sort_type",
            "phone_sort_z_a",
            "plear_read",
            "downloaded",
            "image_damaged",
            "no_datahint",
            "ess_app",
            "clear_smb_confirm",
            "clear_recent_handle_smb",
            "network_error",
            "prompt_smb_file_read_only_info",
            "download_other_app",
            "classical_mode_change_tips",
            "phone_sort_a_z",
            "browse_only",
            "network_location",
            "open_other_app",
            "is_cancel_download",
            "phone_sort_date_old_new",
            "uninstall_application"
    };

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

        List<String> deleteList = new ArrayList<>(Arrays.asList(DELETE_KEYS));
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

