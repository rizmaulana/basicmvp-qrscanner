package id.sheendev.qrbarcodescanner.app.module.generatebarcode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodeActivity;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class GenerateBarcodeActivity extends BaseCodeActivity<GenerateBarcodePresenter> implements GenerateBarcodeView {

    @BindView(R.id.txt_input)
    protected MaterialEditText txtInput;
    @BindView(R.id.btn_generate)
    Button   btnGenerate;
    @BindView(R.id.txt_advanced_config)
    TextView txtAdvancedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_barcode);
        ButterKnife.bind(this);

        presenter = new GenerateBarcodePresenter();
        presenter.setView(this);
        presenter.setType(getIntent().getIntExtra(Constant.CodeType.class.getSimpleName(), Constant.CodeType.Barcode));

    }

    @Override
    public void onCodeTypeSet(int type) {
        switch (type) {
            case Constant.CodeType.Barcode:
                initToolbar(getString(R.string.menu_generate_barcode));
                imgCode.setImageDrawable(getResources().getDrawable(R.drawable.img_barcode_thumb));
              //  txtAdvancedConfig.setVisibility(View.VISIBLE);
                break;
            case Constant.CodeType.QrCode:
                initToolbar(getString(R.string.menu_generate_qrcode));
                imgCode.setImageDrawable(getResources().getDrawable(R.drawable.img_qrcode_thum));
               // txtAdvancedConfig.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onFormError(String message) {
        txtInput.setError(message);

    }

    @Override
    public void onQrGenerated(BarcodeFormat type, String data) {
        generateCode(type, data, imgCode);
        presenter.showIntsAds();
    }

    @OnClick({R.id.btn_generate, R.id.txt_advanced_config})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.btn_generate:
                presenter.generateScannerCode(txtInput.getText().toString());
                break;
            case R.id.txt_advanced_config:
                break;
        }
    }

}
