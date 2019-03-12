package id.sheendev.qrbarcodescanner.app.module.export;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BasePresenter;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.Constant;
import id.sheendev.qrbarcodescanner.shared.util.DateUtils;

/**
 * Created by Rizki Maulana on 16/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class ExportPresenter extends BasePresenter<ExportView> {


    public void export(int exportFormat) {
        getView().showProgressDialog();
        List<ScannerData> scannerDataList = localDb.getAllCodeData();
        if (scannerDataList.size() == 0) {
            getView().showToast(getView().getContext().getString(R.string.err_data_empty));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String        dirPath       = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constant.Dir;
        if (!new File(dirPath).exists()) {
            new File(dirPath).mkdir();
        }
        String filename = "";

        for (ScannerData scannerData : scannerDataList) {
            stringBuilder.append(scannerData.getData());
            stringBuilder.append("\n");
        }
        switch (exportFormat) {
            case Constant.ExportFormat.Xls:
                filename = "QrBarcode_" + DateUtils.getStringTimeStamp() + ".csv";
                break;
            case Constant.ExportFormat.Txt:
                filename = "QrBarcode_" + DateUtils.getStringTimeStamp() + ".txt";
                break;
        }
        try {
            File       file   = new File(dirPath, filename);
            FileWriter writer = new FileWriter(file);
            writer.append(stringBuilder.toString());
            writer.flush();
            writer.close();

            getView().dismissProgressDialog();
            getView().onFileSuccess(exportFormat, file);
        } catch (IOException e) {
            e.printStackTrace();
            getView().onErrorConnectivity();
        }

    }
}
