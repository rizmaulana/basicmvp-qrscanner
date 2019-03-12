package id.sheendev.qrbarcodescanner.app.module.history;

import java.util.List;

import id.sheendev.qrbarcodescanner.app.module.base.BaseView;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public interface HistoryView extends BaseView {


    void onDataSet(List<ScannerData> codeData);

    void updateDataSet();

    void showRefeshIndicator();

    void dismissRefreshIndicator();
}
