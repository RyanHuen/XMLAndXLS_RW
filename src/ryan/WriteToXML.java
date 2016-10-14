
package ryan;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WriteToXML {

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成arrays）
     *
     * @param dstPath    xml路径
     * @param mapToWrite 数据集合
     * @throws IOException
     */
    public static void createXML2Array(String dstPath, Map<String, String> mapToWrite) throws IOException {
        Document document = DocumentHelper.createDocument();
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.addElement("string-array");
        rootElement.addAttribute("name", "recent_from_file_list_chinese");
        for (String key : keys) {
            Element e = rootElement.addElement("item");
//           e.addAttribute("name", mapToWrite.get(key));
            e.setText(mapToWrite.get(key));
        }
        for (String key : keys) {
            Element e = rootElement.addElement("item");
//            e.addAttribute("name", mapToWrite.get(key));
            e.setText(key);
        }
        Writer fileWriter = new FileWriter(dstPath);
        XMLWriter xl = new XMLWriter(fileWriter);
        xl.write(document);
        xl.flush();
        xl.close();
        System.out.println("完成");

    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成Strings.xml）
     *
     * @param dstPath    xml路径
     * @param mapToWrite 数据集合
     * @throws IOException
     */
    public static void createXML2String(String dstPath, Map<String, String> mapToWrite) throws IOException {
        Document document = DocumentHelper.createDocument();
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.addElement("resources");
        HashMap<String, String> map = new HashMap<>();

//        rootElement.addAttribute("name", "recent_from_file_list_chinese"); //给根节点添加参数
        for (String key : keys) {
            if (containKeyToContinue(key)) {
                Element e = rootElement.addElement("string");
                e.addAttribute("name", key);
                e.setText(mapToWrite.get(key));
            }
        }
        Writer fileWriter = new FileWriter(dstPath);
        XMLWriter xl = new XMLWriter(fileWriter);
        xl.write(document);
        xl.flush();
        xl.close();
        System.out.println("完成");

    }

    private static boolean containKeyToContinue(String key) {
        /**
         *     <string name="infos_phoenixos">以 Android 為基礎的個人電腦系統</string>
         <string name="app_slogen">CZ 技術產品</string>
         <string name="other_chaozhuo_application">其他 CZ 應用程式</string>
         <string name="infos_filemanager">像 PC 一樣地管理檔案</string>
         <string name="infos_browser_pad">專為大畫面設計的瀏覽器</string>
         <string name="infos_browser_phone">簡潔而有效率的瀏覽器</string>
         <string name="infos_text_editor">簡潔而有效率的文字編輯器</string>
         */
        String[] strings = {"download_other_app",
                "open_other_app"};
        for (String s : strings) {
            if (s.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
