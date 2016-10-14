
package ryan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 读取每个国家的strings和中文strings比较，寻找出没有进行翻译的内容
 *
 * @author ryanhuenwork
 */
public class CompareStringMain {

    //values-zh-rCN     values-ar
    public static void main(String[] args) throws IOException {
        String cnStringFilePath = "/home/ryanhuenwork/Documents/resources/values_cou/values-zh-rCN/strings.xml";
        Map<String, String> cnMaps = getCountryStrings(
                cnStringFilePath);
        Set<String> cnKeys = cnMaps.keySet();
        File file = new File("/home/ryanhuenwork/Documents/resources/values_cou/");
        File[] files = file.listFiles();
        for (File f : files) {
            File stringsFile = new File(f.getAbsoluteFile() + File.separator + "strings.xml");
            if (!stringsFile.getAbsolutePath().equals(cnStringFilePath)) {
                //表明当前遍历到的文件不是CN语言
                Map<String, String> dstCountryMap = getCountryStrings(stringsFile.getAbsolutePath());
                System.out.println("当前语言：" + f.getName());
                List<String> outPutKeys = new ArrayList<>();
                List<String> outPutValues = new ArrayList<>();
                for (String key : cnKeys) {
                    //遍历中国语言环境的xml字符串，对比每一个语言
                    Set<String> dstCountryKeys = dstCountryMap.keySet();
                    if (!dstCountryKeys.contains(key)) {
                        //目标语言xml中缺少这个字符串
//                        System.out.println(key + "   " + cnMaps.get(key));
                        outPutKeys.add(key);
                        outPutValues.add(cnMaps.get(key));
                    }
                }
                WriteTOExcel.writeToExcelDouble("/home/ryanhuenwork/Documents/resources/lost/" +
                        f.getName() + ".xls", outPutKeys, outPutValues, 0);
            }

        }

    }


    private static Map<String, String> getCountryStrings(String filePath) {
        File cnStrings = new File(filePath);
        ReadFromXML cnReadFromXML = new ReadFromXML(cnStrings);
        return cnReadFromXML.getMaps();
    }

}
