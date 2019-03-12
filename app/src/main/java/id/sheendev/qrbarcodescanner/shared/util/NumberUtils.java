package id.sheendev.qrbarcodescanner.shared.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Rizki Maulana on 11/5/17.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class NumberUtils {

    public static String getCurrency(int data) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return nf.format(data).replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " ");
    }

    public static String getCurrency(String dataS) {
        long data = 0;
        try {
            data = Long.valueOf(dataS);
        } catch (NumberFormatException e) {

        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return nf.format(data).replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " ");
    }


    public static String getNumberFormat(long data) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return nf.format(data).replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " ").replace("Rp", "");
    }


    public static String getNumberFormat(String dataS) {
        long data = 0;
        try {
            data = Long.valueOf(dataS);
        } catch (NumberFormatException e) {

        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return nf.format(data).replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " ").replace("Rp", "");
    }

    public static Double parseDouble(String data) {
        Double aDouble = 0d;
        try {
            aDouble = Double.parseDouble(data);
        } catch (NumberFormatException e) {
            aDouble = 0d;
        }
        return aDouble;
    }

}
