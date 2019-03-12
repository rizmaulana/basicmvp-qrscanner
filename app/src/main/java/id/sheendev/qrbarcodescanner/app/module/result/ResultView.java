package id.sheendev.qrbarcodescanner.app.module.result;

import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodeView;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public interface ResultView extends BaseCodeView {

    void onCodeDataSet(ScannerData codeData);
}
