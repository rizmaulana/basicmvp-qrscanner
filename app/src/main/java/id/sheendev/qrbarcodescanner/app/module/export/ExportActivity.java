package id.sheendev.qrbarcodescanner.app.module.export;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.BuildConfig;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.app.ui.widget.SingleMenuLayout;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class ExportActivity extends BaseActivity<ExportPresenter> implements ExportView {

    @BindView(R.id.menu_export_xls)
    SingleMenuLayout menuExportXls;
    @BindView(R.id.menu_export_txt)
    SingleMenuLayout menuExportTxt;
    @BindView(R.id.img_file)
    ImageView        imgFile;
    @BindView(R.id.btn_open)
    Button           btnOpen;
    @BindView(R.id.btn_share)
    Button           btnShare;
    @BindView(R.id.layout_result)
    LinearLayout     layoutResult;
    @BindView(R.id.txt_dir)
    TextView         txtDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.menu_export_data));

        presenter = new ExportPresenter();
        presenter.setView(this);
    }

    @OnClick({R.id.menu_export_xls, R.id.menu_export_txt, R.id.btn_open, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_export_xls:
                presenter.export(Constant.ExportFormat.Xls);
                presenter.showIntsAds();
                break;
            case R.id.menu_export_txt:
                presenter.export(Constant.ExportFormat.Txt);
                presenter.showIntsAds();
                break;
            case R.id.btn_open:
                Uri uriFile = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constant.Dir + File.separator);
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    intent.setDataAndType(uriFile, "text/csv");
                    startActivity(Intent.createChooser(intent, "Open folder"));
                } catch (ActivityNotFoundException a) {
                    a.printStackTrace();
                    onErrorConnectivity();
                }

                break;
            case R.id.btn_share:
                Uri uri = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", file);
                } else {
                    uri = Uri.fromFile(file);
                }
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("vnd.android.cursor.dir/email");
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_alias_name));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.msg_share_code, "", getString(R.string.app_alias_name), "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=in"));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.app_alias_name)));
                break;
        }
    }

    private File file;

    @Override
    public void onFileSuccess(int exportFormat, File file) {
        this.file = file;
        txtDir.setText("Your file is store into your device "+file.getAbsolutePath());
        switch (exportFormat) {
            case Constant.ExportFormat.Xls:
                imgFile.setImageDrawable(getResources().getDrawable(R.drawable.ic_xls));
                break;
            case Constant.ExportFormat.Txt:
                imgFile.setImageDrawable(getResources().getDrawable(R.drawable.ic_txt));
                break;
        }
    }

    @Override
    public void showProgressDialog() {
        layoutResult.setVisibility(View.GONE);
    }

    @Override
    public void dismissProgressDialog() {
        layoutResult.setVisibility(View.VISIBLE);
    }
}
