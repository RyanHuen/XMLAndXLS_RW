//package ryan.create_string_file;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import ryan.read.ReadFromExcel;
//import ryan.write.WriteToXML;
//
///**
// * Created by ryanhuen on 17-4-10.
// */
//public class CreateStringsXMLFromxls {
//    public static void createStringsFile() {
//        File file = new File("/home/ryanhuen/Documents/explorer/res");
//        ReadFromExcel excel=new ReadFromExcel(new File("/home/ryanhuen/Documents/explorer/test.xls"));
//        List<String> list=excel.readExcel(0,0);
//        for (int i = 0; i < list.size(); i++) {
//            String countryCode= list.get(i);
//            File countryFile=new File(file.getAbsolutePath()+"/values-"+countryCode);
//            if(!countryFile.exists()){
//                countryFile.mkdirs();
//            }
//            File countryString=new File(countryFile.getAbsolutePath()+"/strings.xml");
//            if(!countryString.exists()){
//                try {
//                    Map<String, String> map = new HashMap<>();
//                    WriteToXML.createXML2String(countryString.getAbsolutePath(), map);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        createStringsFile();
//    }
//}
