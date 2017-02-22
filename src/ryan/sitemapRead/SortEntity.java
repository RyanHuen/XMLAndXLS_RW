package ryan.sitemapRead;

/**
 * Created by ryanhuencompany on 17-2-22.
 */
public class SortEntity implements Comparable<SortEntity>{
    public String url;

    public SortEntity(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(SortEntity o) {
        char[] o_chars=o.url.toCharArray();
        char[] t_chars=url.toCharArray();
        for (int i = 0; i < t_chars.length; i++) {
            char t_char = t_chars[i];
            if(t_char>o_chars[i]){
                return 1;
            }else if(t_char<o_chars[i]){
                return -1;
            }
        }
        return 0;
    }
}
