package id.sheendev.qrbarcodescanner.app.module.generatebarcode;

import com.google.zxing.BarcodeFormat;

import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodePresenter;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.Constant;

/**
 * Created by Rizki Maulana on 06/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class GenerateBarcodePresenter extends BaseCodePresenter<GenerateBarcodeView> {
    private int type;

    public void setType(int type) {
        this.type = type;
        getView().onCodeTypeSet(type);
    }

    public void generateScannerCode(String data) {
        if (data.isEmpty()) {
            getView().onFormError(getView().getContext().getString(R.string.msg_plese_input_text));
        } else if (type == Constant.CodeType.Barcode && data.length() > 80) {
            getView().onFormError(getView().getContext().getString(R.string.msg_barcode_char_limit));
        } else {
            ScannerData scannerData = new ScannerData(data);
            saveScannerData(scannerData);
            switch (type) {
                case Constant.CodeType.Barcode:
                    getView().onQrGenerated(BarcodeFormat.CODE_128, data);
                    break;
                case Constant.CodeType.QrCode:
                    getView().onQrGenerated(BarcodeFormat.QR_CODE, data);
                    break;
            }
        }
    }
}
