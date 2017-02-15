package ryan.sitemapRead;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ryanhuencompany on 17-2-15.
 */
public class ReadMain {
    private final static String OUTPUT_URLS = "/home/ryanhuencompany/Blog/test/urls.txt";

    public static void main(String[] args) {
        SiteMapRead siteMapRead = new SiteMapRead(new File("/home/ryanhuencompany/Blog/test/.deploy_git/sitemap.xml"));
        List<String> mUrls = siteMapRead.getSiteMapUrls();
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
