
package tech.ryanhuen.base;

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
import java.util.*;

public class XMLWriters {
    public static void appendToXML(String dstPath, Map<String, String> mapToWrite) {
        Document document = getSaxDocument(dstPath);
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.getRootElement();
        makeElement(mapToWrite, keys, rootElement);
        write2File(dstPath, document);
    }

    private static Document getSaxDocument(String dstPath) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new File(dstPath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成arrays）
     *
     * @param xmlFilePath xml路径
     * @param mapToWrite  数据集合
     * @throws IOException
     */
    public static void parseMap2XMLArray(String xmlFilePath, String arrayName, Map<String, String> mapToWrite) {
        Document document = DocumentHelper.createDocument();
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.addElement("string-array");
        rootElement.addAttribute("name", arrayName);
        for (String key : keys) {
            Element e = rootElement.addElement("item");
            e.addAttribute("name", mapToWrite.get(key));
            e.setText(mapToWrite.get(key));
        }
        Writer fileWriter = null;
        org.dom4j.io.XMLWriter xl = null;
        try {
            fileWriter = new FileWriter(xmlFilePath);
            xl = new org.dom4j.io.XMLWriter(fileWriter);
            xl.write(document);
            xl.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            release(fileWriter, xl);
        }
    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成arrays）
     *
     * @param xmlFilePath xml路径
     * @param mapToWrite  数据集合
     * @throws IOException
     */
    public static void map2ArrayAppend2XML(String xmlFilePath, String arrayName, LinkedHashMap<String, String> mapToWrite) {
        Document document = getSaxDocument(xmlFilePath);
        Element rootElement = document.getRootElement();
        Element dstElement = rootElement.addElement("string-array");
        dstElement.addAttribute("name", arrayName);
        Iterator it = mapToWrite.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entity = (Map.Entry) it.next();
            Element e = dstElement.addElement("item");
            e.setText((String) entity.getValue());
        }
        write2File(xmlFilePath, document);

    }

    private static void write2File(String xmlFilePath, Document document) {
        Writer fileWriter = null;
        org.dom4j.io.XMLWriter xl = null;
        try {
            fileWriter = new FileWriter(xmlFilePath);
            xl = new org.dom4j.io.XMLWriter(fileWriter);
            xl.write(document);
            xl.flush();
            xl.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            release(fileWriter, xl);
        }
    }

    private static void release(Writer fileWriter, org.dom4j.io.XMLWriter xl) {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
            if (xl != null) {
                xl.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传入xml路径和集合，从集合中把数据转换成xml（写成Strings.xml）
     *
     * @param dstPath    xml路径
     * @param mapToWrite 数据集合
     * @throws IOException
     */
    public static void createXML2String(String dstPath, Map<String, String> mapToWrite) {
        Document document = DocumentHelper.createDocument();
        Set<String> keys = mapToWrite.keySet();
        Element rootElement = document.addElement("resources");
        makeElement(mapToWrite, keys, rootElement);
        write2File(dstPath, document);

    }

    private static void makeElement(Map<String, String> mapToWrite, Set<String> keys, Element rootElement) {
        for (String key : keys) {
            if (!skipKeys(key)) {
                Element e = rootElement.addElement("string");
                e.addAttribute("name", key);
                e.setText(mapToWrite.get(key));
            }
        }
    }

    /**
     * 不包含数组中指定的某些key的字符串，因为有些可能已经翻译好添加过了
     *
     * @param key
     * @return
     */
    private static boolean skipKeys(String key) {

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
