package ryan.xls_to_property;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import ryan.read.ReadFromExcel;

/**
 * Created by ryanhuenwork on 16-10-14.
 */
public class GenerateProperties {
    /**
     * 生成的property文件的路径
     */
    public static final String PROPERTIES_FILE_OUTPUT_PATH = "/home/ryanhuenwork/Desktop/tv_devices.properties";
    /**
     * 从哪个excel文件中读取
     */
    public static final String READ_FROM_EXCEL_FILE_PATH = "/home/ryanhuenwork/Desktop/tv_device.xls";

    /**
     * 写出到.properties文件，形式：
     * <p>
     * content = content
     *
     * @param args
     */
    public static void main(String[] args) {
        ReadFromExcel readFromExcel = new ReadFromExcel(new File(READ_FROM_EXCEL_FILE_PATH));
        Map<String, String> fromXLS = readFromExcel.readExcel(0, 1, 2);
        Properties properties = new Properties();

        Set<String> keys = fromXLS.keySet();
        for (String key : keys) {
            properties.setProperty(key.toLowerCase(), fromXLS.get(key).toLowerCase());
        }
        File outPutFile = new File(PROPERTIES_FILE_OUTPUT_PATH);
        try {
            outPutFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = new FileOutputStream(outPutFile);
            properties.store(outputStream, "null");
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
