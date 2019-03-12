package id.sheendev.qrbarcodescanner.app.module.history;

import java.util.List;

import id.sheendev.qrbarcodescanner.app.module.base.BasePresenter;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class HistoryPresenter extends BasePresenter<HistoryView> {
    List<ScannerData> codeDataList;


    public void setScannerData() {
        codeDataList = localDb.getAllCodeData();
        getView().onDataSet(codeDataList);


    }

    public void deleteScannerData(ScannerData scannerData) {
        localDb.deleteScannerData(scannerData);
        codeDataList.remove(scannerData);
        getView().updateDataSet();

    }
}
