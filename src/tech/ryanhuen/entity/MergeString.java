package tech.ryanhuen.entity;

/**
 * Created by ryanhuencompany on 17-1-4.
 */
public class MergeString {
    public String key;
    public String firstCountry;
    public String secondCountry;

    public MergeString(String key, String firstCountry, String secondCountry) {
        this.key = key;
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
    }

    @Override
    public String toString() {
        return "MergeString{" +
                "key='" + key + '\'' +
                ", firstCountry='" + firstCountry + '\'' +
                ", secondCountry='" + secondCountry + '\'' +
                '}';
    }
}
