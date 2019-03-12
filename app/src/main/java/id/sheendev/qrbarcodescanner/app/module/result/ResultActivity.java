package id.sheendev.qrbarcodescanner.app.module.result;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodeActivity;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class ResultActivity extends BaseCodeActivity<ResultPresenter> implements ResultView {

    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.txt_result)
    TextView  txtResult;
    @BindView(R.id.switch_barcode)
    Switch    switchBarcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        presenter = new ResultPresenter();
        presenter.setView(this);
        presenter.setCodeData(getIntent().getParcelableExtra(Constant.StaticVariable.Result), getIntent().getBooleanExtra(Constant.StaticVariable.SaveCodeData, false));
        presenter.setupCodeData();

        switchBarcode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imgQrcode.setVisibility(View.GONE);
                imgCode.setVisibility(View.VISIBLE);
            } else {
                imgQrcode.setVisibility(View.VISIBLE);
                imgCode.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onCodeDataSet(ScannerData codeData) {
        this.codeData = codeData.getData();
        txtResult.setText(codeData.getData());
        if (codeData.getData().length() < 80) {
            generateCode(BarcodeFormat.CODE_128, codeData.getData(), imgCode);
        }
        generateCode(BarcodeFormat.QR_CODE, codeData.getData(), imgQrcode);
    }
}
