package id.sheendev.qrbarcodescanner.app.module.base.basecode;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.BuildConfig;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.shared.util.BitmapUtils;
import id.sheendev.qrbarcodescanner.shared.util.MediaScannerUtil;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public abstract class BaseCodeActivity<V extends BaseCodePresenter> extends BaseActivity<V> implements BaseCodeView {
    @Nullable
    @BindView(R.id.img_code)
    protected ImageView imgCode;
    @Nullable
    @BindView(R.id.btn_copy)
    protected Button    btnCopy;
    @Nullable
    @BindView(R.id.btn_share)
    protected Button    btnShare;
    @Nullable
    @BindView(R.id.btn_open)
    protected Button    btnOpen;
    @Nullable
    @BindView(R.id.img_qrcode)
    protected ImageView imgQrcode;
    @Nullable
    @BindView(R.id.btn_save_image)
    protected Button    btnSaveImage;

    protected String codeData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void generateCode(BarcodeFormat type, String data, ImageView imageView) {
        codeData = data;
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix         bmpMatrix         = null;
            bmpMatrix = multiFormatWriter.encode(data, type, 256, 256);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap         bitmap         = barcodeEncoder.createBitmap(bmpMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    protected String decodeFromBitmap(Bitmap bitmap) {
        String data = "";
        try {
            int   width  = bitmap.getWidth(), height = bitmap.getHeight();
            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            bitmap.recycle();
            RGBLuminanceSource source  = new RGBLuminanceSource(width, height, pixels);
            BinaryBitmap       bBitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader  reader  = new MultiFormatReader();
            Result             result  = reader.decode(bBitmap);
            data = result.toString();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return data;

    }


    @OnClick({R.id.btn_copy, R.id.btn_share, R.id.btn_open, R.id.btn_save_image})
    public void onViewClicked(View view) {
        if (codeData == null) return;

        switch (view.getId()) {
            case R.id.btn_copy:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", codeData);
                clipboard.setPrimaryClip(clip);
                showToast(getString(R.string.ms_copy_clipboard));
                break;
            case R.id.btn_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_alias_name));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.msg_share_code, codeData, getString(R.string.app_alias_name), "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=in"));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.app_alias_name)));
                break;
            case R.id.btn_open:
                String url = codeData;
                if (!codeData.contains("http://") || !codeData.contains("https://")) {
                    url = "https://www.google.com/search?q=" + codeData;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btn_save_image:
                File file = null;
                if (imgCode != null && imgCode.getVisibility() == View.VISIBLE) {
                    file = BitmapUtils.takeScreenshoot(codeData, imgCode, imgCode.getWidth(), imgCode.getHeight());
                }
                if (imgQrcode != null && imgQrcode.getVisibility() == View.VISIBLE) {
                    file = BitmapUtils.takeScreenshoot(codeData, imgQrcode, imgQrcode.getWidth(), imgQrcode.getHeight());
                }
                if (file != null) {
                    new MediaScannerUtil(getApplicationContext(), file);
                }
                showToast(getString(R.string.xml_image_svaed_to_device));

                break;
        }
    }
}
