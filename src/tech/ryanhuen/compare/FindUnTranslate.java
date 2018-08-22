
package tech.ryanhuen.compare;

import tech.ryanhuen.base.ExcelWriter;
import tech.ryanhuen.base.XMLReader;
import tech.ryanhuen.utils.PropertyHelper;

import java.io.*;
import java.util.*;

/**
 * 读取每个国家的strings和中文strings比较，寻找出没有进行翻译的内容
 *
 * @author ryanhuenwork
 */
public class FindUnTranslate {
    private String mZH_CNDirPath;
    private List<File> mStringFiles = new ArrayList<>();
    private String mOutPutDir;
    private List<String> mIgnoreKeys;

    public FindUnTranslate(String resDirPath, String outputDir) {
        mZH_CNDirPath = resDirPath + "/values-zh-rCN/strings.xml";
        mOutPutDir = outputDir;
        mIgnoreKeys = PropertyHelper.parseProperties2List("config/string_ignore.properties");
        readAllStringFileUnderRes(new File(resDirPath));
    }

    public void find() throws IOException {
        XMLReader zhCNReader = new XMLReader(new File(mZH_CNDirPath));
        Map<String, String> zhCNStringMaps = zhCNReader.readXMLContent();
        Set<String> cnKeys = zhCNReader.getKeys();
        for (File f : mStringFiles) {
            if (!f.getAbsolutePath().equals(mZH_CNDirPath)) {
                //表明当前遍历到的文件不是CN语言
                Map<String, String> dstCountryMap = getTargetCountryStrings(f.getAbsolutePath());
                List<String> outPutKeys = new ArrayList<>();
                List<String> outPutValues = new ArrayList<>();
                for (String key : cnKeys) {
                    //遍历中国语言环境的xml字符串，对比每一个语言
                    Set<String> dstCountryKeys = dstCountryMap.keySet();
                    if (!dstCountryKeys.contains(key) && !mIgnoreKeys.contains(key)) {
                        //目标语言xml中缺少这个字符串
                        outPutKeys.add(key);
                        outPutValues.add(zhCNStringMaps.get(key));
                    }
                }
                ExcelWriter.writeToExcelDouble(mOutPutDir + File.separator + f.getParentFile().getName() + ".xls", outPutKeys, outPutValues, 0);
            }
        }
    }

    private void readAllStringFileUnderRes(File file) {
        if (file != null && file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        readAllStringFileUnderRes(f);
                    } else {
                        if (f.getName().trim().equals("strings.xml")) {
                            mStringFiles.add(f);
                        }
                    }
                }
            }
        }
    }


    private static Map<String, String> getTargetCountryStrings(String filePath) {
        XMLReader targetReader = new XMLReader(new File(filePath));
        return targetReader.readXMLContent();
    }

}
