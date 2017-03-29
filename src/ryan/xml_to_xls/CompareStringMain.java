
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
     * 要把没有翻译过的字符串写出去的excel文件所在路径，不包括excel文件名称
     */
    public static final String OUTPUT_EXCEL_FILE_PATH = "/home/ryanhuen/Documents/explorer/out";
    /**
     * 指定从项目中拷贝出来的res目录的位置,不包含res
     */
    public static final String ANDROID_STRINGS_FILE_PATH = "/home/ryanhuen/Documents/explorer";

    //values-zh-rCN     values-ar
    public static void main(String[] args) throws IOException {
        String cnStringFilePath = ANDROID_STRINGS_FILE_PATH + "/res/values-zh-rCN/strings.xml";
        Map<String, String> cnMaps = getCountryStrings(
                cnStringFilePath);
        Set<String> cnKeys = cnMaps.keySet();
        File file = new File(ANDROID_STRINGS_FILE_PATH);
        listFileToSearchStringXML(file);
        for (File f : stringsFileList) {
            if (!f.getAbsolutePath().equals(cnStringFilePath)) {
                //表明当前遍历到的文件不是CN语言
                Map<String, String> dstCountryMap = getCountryStrings(f.getAbsolutePath());
                System.out.println("当前语言：" + f.getParentFile().getName());
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
                WriteTOExcel.writeToExcelDouble(OUTPUT_EXCEL_FILE_PATH + File.separator +
                        f.getParentFile().getName().substring(f.getParentFile().getName().indexOf("-") + 1) + ".xls", outPutKeys, outPutValues, 0);
            }

        }

    }


    private static Map<String, String> getCountryStrings(String filePath) {
        File cnStrings = new File(filePath);
        ReadFromXML cnReadFromXML = new ReadFromXML(cnStrings);
        return cnReadFromXML.getMaps();
    }

    public static List<File> stringsFileList = new ArrayList<>();

    public static void listFileToSearchStringXML(File file) {
        File[] file1 = file.listFiles();
        for (File f : file1) {
            if (f.isDirectory()) {
                listFileToSearchStringXML(f);
            } else {
                if (f.getName().trim().equals("strings.xml")) {
                    stringsFileList.add(f);
                }
            }

        }
    }

}
