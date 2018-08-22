
package tech.ryanhuen.base;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLReader {
    private SAXReader reader = new SAXReader();
    private File mFile;

    public XMLReader(File file) {
        mFile = file;
    }

    public Map<String, String> readXMLContent() {
        HashMap<String, String> contentMaps = new HashMap<>();
        if (mFile.exists()) {
            Document document = null;
            try {
                document = reader.read(mFile);
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // 读取XML文件
            Element root = document.getRootElement();// 得到根节点
            List<Element> list = root.elements();
            for (Element element : list) {
                Attribute attr = element.attribute(0);
                String key = attr.getData().toString().trim();
                String value = element.getStringValue().trim();
                contentMaps.put(key, value);
            }
        }
        return contentMaps;
    }

    public Set<String> getKeys() {
        Map<String, String> contentMaps = this.readXMLContent();
        return contentMaps.keySet();
    }

}
