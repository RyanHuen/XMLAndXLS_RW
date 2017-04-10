package ryan.get_src_file;

import java.io.File;

/**
 * Created by ryanhuen on 17-3-23.
 */
public class GetSrcFile {
    public static void main(String[] args) {
        File file = new File("/home/ryanhuen/Project/Browser/src");
        if (file.exists() && file.isDirectory()) {
            searchSubSrcDir(file);
            System.out.println("+++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++");
            searchSubJarFile(file);
        }
    }

    public static void searchSubSrcDir(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File subFile = files[i];
            if (subFile.isDirectory() && !subFile.getPath().endsWith("/src") ) {
                searchSubSrcDir(subFile);
            } else if (!subFile.isDirectory()) {
                continue;
            } else {
                if (!subFile.getAbsolutePath().contains("test")&&!subFile.getAbsolutePath().contains("/out/")) {
                    System.out.println("<sourceFolder url=\"file://$MODULE_DIR$" + subFile.getAbsolutePath().substring(34) + "\" isTestSource=\"false\"/>");
                }
            }
        }

    }

    public static void searchSubJarFile(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File subFile = files[i];
            if (subFile.isDirectory()) {
                searchSubJarFile(subFile);
            } else if (!subFile.isDirectory() && subFile.getAbsolutePath().endsWith(".jar")) {
                System.out.println("<sourceFolder url=\"file://$MODULE_DIR$" + subFile.getAbsolutePath().substring(34) + "\" isTestSource=\"false\" generated=\"true\" />");
            } else {
                continue;
            }
        }

    }
}
