package ryan.sitemapRead;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ryanhuencompany on 17-2-15.
 */
public class ReadMain {
    private final static String OUTPUT_URLS = "/home/ryanhuencompany/Blog/hexo/urls.txt";

    public static void main(String[] args) {
        SiteMapRead siteMapRead = new SiteMapRead(new File("/home/ryanhuencompany/Blog/hexo/.deploy_git/sitemap.xml"));
        List<String> mUrls = siteMapRead.getSiteMapUrls();
        List<SortEntity> mEntities=new ArrayList<>();
        for (int i = 0; i < mUrls.size(); i++) {
            mEntities.add(new SortEntity(mUrls.get(i)));
        }
        Collections.sort(mEntities);
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_URLS));
            for (int i = 0; i < mUrls.size(); i++) {
                String s = mUrls.get(i);
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
