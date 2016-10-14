
package ryan.read;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReadFromXML {
    private HashMap<String, String> mapFromStrings = new HashMap<>();
    private SAXReader reader = new SAXReader();
    private File mFile;
    private Set<String> keys;

    public ReadFromXML(File file) {
        mFile = file;
    }

    private void readXML() {
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
                mapFromStrings.put(key, value);
            }
        }
        keys = mapFromStrings.keySet();
    }

    public Set<String> getKeys() {
        this.readXML();
        return keys;
    }

    public HashMap getMaps() {
        this.readXML();
        return mapFromStrings;

    }

}
