package id.sheendev.qrbarcodescanner.app.module.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.BuildConfig;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.app.ui.widget.SingleMenuLayout;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutView {

    @BindView(R.id.menu_rate)
    SingleMenuLayout menuRate;
    @BindView(R.id.menu_email)
    SingleMenuLayout menuEmail;
    @BindView(R.id.menu_share)
    SingleMenuLayout menuShare;
    @BindView(R.id.txt_version)
    TextView         txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.menu_setting));

        txtVersion.setText(BuildConfig.VERSION_NAME);
    }

    @OnClick({R.id.menu_rate, R.id.menu_email, R.id.menu_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_rate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
                }
                break;
            case R.id.menu_email:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("vnd.android.cursor.dir/email");
                String to[] = {Constant.Email};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_alias_name));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.app_alias_name)));
                break;
            case R.id.menu_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_alias_name));
                sharingIntent.putExtra(Intent.EXTRA_TEXT,  getString(R.string.app_alias_name, "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=in"));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.app_alias_name)));
                break;
        }
    }
}
