package ryan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllCountry {

    /**
     * 获取指定目录下所有国家码（适用于Android工程）  获取到如：values-uk 中的：uk
     *
     * @param dstfile               需要遍历国家码的目录
     * @param countryCodeXlsToWrite 获取到国家码后，写出的xls文件
     */
    public static void getAllCountryByPath(File dstfile, String countryCodeXlsToWrite) {
        File[] files = dstfile.listFiles();
        List<String> countryList = new ArrayList<>();
        for (File file : files) {
            String name = file.getName();
            String country_endfix = name.substring(name.indexOf("-") + 1, name.length());
            countryList.add(country_endfix);
        }
        WriteTOExcel.writeToExcelSingle(countryCodeXlsToWrite, countryList, 1);
    }

}
