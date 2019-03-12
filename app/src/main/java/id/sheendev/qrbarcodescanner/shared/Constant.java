package id.sheendev.qrbarcodescanner.shared;

/**
 * Created by Rizki Maulana on 01/04/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class Constant {

    public interface CodeType {
        int Barcode = 0;
        int QrCode  = 1;
    }

    public interface StorageData {
        int Local  = 0;
        int Server = 1;
    }

    public interface ActivityResult {
        int GoogleSignIn = 100;
    }

    public interface StaticVariable {
        String Result       = "result";
        String SaveCodeData = "save_code_data";

    }

    public interface Ads {
        String Intertitial = "ca-app-pub-1604320506232946/3580839017";
        String Banner      = "ca-app-pub-1604320506232946/3504817392";
        String AdsId       = "ca-app-pub-1604320506232946~3030174677";
    }

    public interface ExportFormat {
        int Xls = 0;
        int Txt = 1;
    }

    public static final String Email = "sheendev.id@gmail.com";
    public static final String Mode  = "";
    public static final String Dir   = "AdvancedQRBarcode";

}
