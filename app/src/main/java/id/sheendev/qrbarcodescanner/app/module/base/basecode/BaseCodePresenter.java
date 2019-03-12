package id.sheendev.qrbarcodescanner.app.module.base.basecode;

import id.sheendev.qrbarcodescanner.app.module.base.BasePresenter;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public abstract class BaseCodePresenter<V extends BaseCodeView> extends BasePresenter<V> {

    public void saveScannerData(ScannerData scannerData) {
        localDb.saveCodeData(scannerData);


    }


}
