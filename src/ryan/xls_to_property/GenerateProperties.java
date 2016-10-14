package ryan.xls_to_property;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import ryan.read.ReadFromExcel;

/**
 * Created by ryanhuenwork on 16-10-14.
 */
public class GenerateProperties {
    /**
     * 生成的property文件的路径
     */
    public static final String PROPERTIES_FILE_OUTPUT_PATH = "/home/ryanhuenwork/Desktop/tv_devices.property";
    /**
     * 从哪个excel文件中读取
     */
    public static final String READ_FROM_EXCEL_FILE_PATH = "/home/ryanhuenwork/Desktop/tv_device.xls";

    /**
     * 写出到.property文件，形式：
     * <p>
     * content = content
     *
     * @param args
     */
    public static void main(String[] args) {
        ReadFromExcel readFromExcel = new ReadFromExcel(new File(READ_FROM_EXCEL_FILE_PATH));
        Map<String, String> fromXLS = readFromExcel.readExcel(0, 1, 2);
        PrintWriter printWriter = null;
        try {
            File outPutFile = new File(PROPERTIES_FILE_OUTPUT_PATH);
            outPutFile.createNewFile();
            printWriter = new PrintWriter(outPutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> keys = fromXLS.keySet();
        for (String key : keys) {
            printWriter.append(key).append(" = ").append(fromXLS.get(key)).append("\n");
        }
        printWriter.flush();
        printWriter.close();

    }
}
