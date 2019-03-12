package id.sheendev.qrbarcodescanner.app.module.export;

import java.io.File;

import id.sheendev.qrbarcodescanner.app.module.base.BaseView;

/**
 * Created by Rizki Maulana on 16/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public interface ExportView extends BaseView {

    void onFileSuccess(int exportFormat, File file);
}
