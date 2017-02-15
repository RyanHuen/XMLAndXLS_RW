package ryan.sitemapRead;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanhuencompany on 17-2-15.
 */
public class SiteMapRead {
    private List<String> mSiteMapUrls=new ArrayList<>();
    private SAXReader reader = new SAXReader();
    private File mFile;

    public SiteMapRead(File file) {
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
                List<Element> details=element.elements();
                for (int i = 0; i < details.size(); i++) {
                    Element element1 = details.get(i);
                    if(element1.getName().equals("loc")){
                        mSiteMapUrls.add(element1.getData().toString());
                    }
                }
            }
        }
    }

    public List<String> getSiteMapUrls(){
        this.readXML();
        return mSiteMapUrls;
    }

}
