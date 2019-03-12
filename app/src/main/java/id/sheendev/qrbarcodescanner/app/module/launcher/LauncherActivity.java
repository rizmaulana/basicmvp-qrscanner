package id.sheendev.qrbarcodescanner.app.module.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import butterknife.ButterKnife;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.app.module.home.HomeActivity;

public class LauncherActivity extends BaseActivity<LauncherPresenter> implements LauncherView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        presenter = new LauncherPresenter();
        presenter.setView(this);

        PermissionsManager.getInstance().
                requestAllManifestPermissionsIfNecessary(LauncherActivity.this,
                        new PermissionsResultAction() {
                            @Override
                            public void onGranted() {
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                            }

                            @Override
                            public void onDenied(String permission) {
                                Toast.makeText(LauncherActivity.this, R.string.err_permission_denied, Toast.LENGTH_SHORT).show();
                            }
                        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


}



