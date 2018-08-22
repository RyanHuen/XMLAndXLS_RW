package tech.ryanhuen;

import tech.ryanhuen.compare.FindUnTranslate;
import tech.ryanhuen.utils.Configs;

import java.io.IOException;

public class Main {

    public static final String RES_DIR = "res_dir";
    public static final String COMPARE_OUT_PUT_DIR = "compare_out_put_dir";

    public static void main(String[] args) {
        FindUnTranslate compareString = new FindUnTranslate(
                Configs.getInstance().getConfig(RES_DIR),
                Configs.getInstance().getConfig(COMPARE_OUT_PUT_DIR));
        try {
            compareString.find();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
