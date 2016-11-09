
package ryan.write;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WriteToXML {
    public static void appendToXML(String dstPath, Map<String, String> mapToWrite) throws IOException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(dstPath));
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.getRootElement();

//        rootElement.addAttribute("name", "recent_from_file_list_chinese"); //给根节点添加参数
        for (String key : keys) {
//            if (!containKeyToContinue(key)) {
            Element e = rootElement.addElement("string");
            e.addAttribute("name", key);
            e.setText(mapToWrite.get(key));
//            }
        }
        Writer fileWriter = new FileWriter(dstPath);
        XMLWriter xl = new XMLWriter(fileWriter);
        xl.write(document);
        xl.flush();
        xl.close();
        System.out.println("完成");
    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成arrays）
     *
     * @param dstPath    xml路径
     * @param mapToWrite 数据集合
     * @throws IOException
     */
    public static void createXML2Array(String dstPath, String arrayName, Map<String, String> mapToWrite) throws IOException {
        Document document = DocumentHelper.createDocument();
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.addElement("string-array");
        rootElement.addAttribute("name", arrayName);
        for (String key : keys) {
            Element e = rootElement.addElement("item");
            e.addAttribute("name", mapToWrite.get(key));
            e.setText(mapToWrite.get(key));
        }
        Writer fileWriter = new FileWriter(dstPath);
        XMLWriter xl = new XMLWriter(fileWriter);
        xl.write(document);
        xl.flush();
        xl.close();
        System.out.println("完成");

    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成arrays）
     *
     * @param dstPath    xml路径
     * @param mapToWrite 数据集合
     * @throws IOException
     */
    public static void appenArrays2XML(String dstPath, String arrayName, LinkedHashMap<String, String> mapToWrite) throws IOException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(dstPath));
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.getRootElement();
        Element dstElement = rootElement.addElement("string-array");
        dstElement.addAttribute("name", arrayName);
        Iterator it = mapToWrite.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entity = (Map.Entry) it.next();
            Element e = dstElement.addElement("item");
            e.setText((String) entity.getValue());
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
            if (!containKeyToContinue(key)) {
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

    /**
     * 不包含数组中指定的某些key的字符串，因为有些可能已经翻译好添加过了
     *
     * @param key
     * @return
     */
    private static boolean containKeyToContinue(String key) {

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
