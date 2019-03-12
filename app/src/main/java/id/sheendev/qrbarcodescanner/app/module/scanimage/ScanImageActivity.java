package id.sheendev.qrbarcodescanner.app.module.scanimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.basecode.BaseCodeActivity;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ScanImageActivity extends BaseCodeActivity<ScanImagePresenter> implements ScanImageView {

    @BindView(R.id.btn_pick_image)
    Button   btnPickImage;
    @BindView(R.id.txt_result)
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_image);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.menu_scan_image));

        presenter = new ScanImagePresenter();
        presenter.setView(this);
    }


    @OnClick(R.id.btn_pick_image)
    public void onViewClicked() {
        codeData = null;
        txtResult.setText("");
        imgCode.setImageDrawable(getResources().getDrawable(R.drawable.img_qrcode_thum));
        EasyImage.openGallery(this, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                onErrorConnectivity();
            }

            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                File                  image   = imageFiles.get(0);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;

                Bitmap bitmap     = BitmapFactory.decodeFile(image.getAbsolutePath(), options);
                String result     = decodeFromBitmap(bitmap);
                if (!result.isEmpty()) {
                    codeData = result;
                    txtResult.setText(result);

                    presenter.showIntsAds();
                    presenter.saveScannerData(new ScannerData(result));
                } else {
                    onErrorConnectivity();
                }
            }


            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                onErrorConnectivity();
            }
        });
    }
}
