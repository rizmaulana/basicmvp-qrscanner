package id.sheendev.qrbarcodescanner.app.module.generatebarcode;

import com.google.zxing.BarcodeFormat;

import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodeView;

/**
 * Created by Rizki Maulana on 06/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public interface GenerateBarcodeView extends BaseCodeView {

    void onCodeTypeSet(int type);

    void onFormError(String message);

    void onQrGenerated(BarcodeFormat type, String data);

}
