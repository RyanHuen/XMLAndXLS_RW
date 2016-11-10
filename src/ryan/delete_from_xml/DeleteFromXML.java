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
    public static final String ANDROID_STRINGS_FILE_PATH = "/home/ryanhuencompany/Documents/explorer";

    public static final String[] DELETE_KEYS = {"tips",
            "app_update_dlg_update_description",
            "app_update_tip_error_version_ignored",
            "app_update_download_notification_desc",
            "app_update_dlg_title",
            "app_update_patch_notification_desc",
            "app_update_dlg_cb_text",
            "app_update_dlg_do_not_update",
            "app_update_tip_failed_to_load_check_for_update_file",
            "app_update_tip_already_up_to_date",
            "app_update_dlg_version_and_size",
            "app_update_warn_download_manager_disabled",
            "app_update_dlg_update_now",
            "app_update_tip_error_update_already_in_process",
            "app_update_tip_error_no_network",
            "app_update_tip_error_handling_ret_data",
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
