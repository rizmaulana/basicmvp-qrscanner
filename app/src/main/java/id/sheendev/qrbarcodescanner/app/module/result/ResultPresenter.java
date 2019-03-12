package id.sheendev.qrbarcodescanner.app.module.result;

import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodePresenter;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class ResultPresenter extends BaseCodePresenter<ResultView> {
    private ScannerData codeData;

    public void setCodeData(ScannerData codeData, boolean saveCodeData) {
        this.codeData = codeData;

        if (saveCodeData) {
            saveScannerData(codeData);
        }

    }


    public void setupCodeData() {
        getView().onCodeDataSet(codeData);

    }
}
