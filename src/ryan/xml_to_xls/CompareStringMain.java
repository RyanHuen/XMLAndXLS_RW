
package ryan.xml_to_xls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ryan.read.ReadFromXML;
import ryan.write.WriteTOExcel;

/**
 * 读取每个国家的strings和中文strings比较，寻找出没有进行翻译的内容
 *
 * @author ryanhuenwork
 */
public class CompareStringMain {
    /**
     * 需要进行对比的中文字符串文件strings.xml
     */
    public static final String STRING_FILE_PATH_ZH_CN = "/home/ryanhuenwork/Documents/resources/values_cou/values-zh-rCN/strings.xml";
    /**
     * 要把数据写入的excel文件所在的路径，不包括excel文件名称
     */
    public static final String OUTPUT_EXCEL_FILE_PATH = "/home/ryanhuenwork/Documents/resources/lost/";

    //values-zh-rCN     values-ar
    public static void main(String[] args) throws IOException {
        String cnStringFilePath = STRING_FILE_PATH_ZH_CN;
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
                WriteTOExcel.writeToExcelDouble(OUTPUT_EXCEL_FILE_PATH +
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