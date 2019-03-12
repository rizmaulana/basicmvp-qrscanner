package id.sheendev.qrbarcodescanner.app.module.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.about.AboutActivity;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.app.module.export.ExportActivity;
import id.sheendev.qrbarcodescanner.app.module.generatebarcode.GenerateBarcodeActivity;
import id.sheendev.qrbarcodescanner.app.module.history.HistoryActivity;
import id.sheendev.qrbarcodescanner.app.module.result.ResultActivity;
import id.sheendev.qrbarcodescanner.app.module.scanimage.ScanImageActivity;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class HomeActivity extends BaseActivity<HomePresenter>
        implements NavigationView.OnNavigationItemSelectedListener, HomeView, BarcodeCallback {

    @BindView(R.id.scanner)
    CompoundBarcodeView scanner;
    @BindView(R.id.img_flash)
    ImageView           imgFlash;

    private TextView     txtUsername;
    private TextView     txtEmail;
    private boolean      isFlashOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initToolbarAndNavigation();

        scanner.setStatusText("");
        scanner.decodeContinuous(this);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            imgFlash.setVisibility(View.GONE);
        }

        presenter = new HomePresenter();
        presenter.setView(this);
        presenter.showBannerAds();

    }


    private void initToolbarAndNavigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }

        txtUsername = navigationView.getHeaderView(0).findViewById(R.id.txt_username);
        txtEmail = navigationView.getHeaderView(0).findViewById(R.id.txt_email);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                break;

            case R.id.action_gen_barcode:
                startActivity(new Intent(getApplicationContext(), GenerateBarcodeActivity.class).putExtra(Constant.CodeType.class.getSimpleName(), Constant.CodeType.Barcode));
                break;

            case R.id.action_gen_qrcode:
                startActivity(new Intent(getApplicationContext(), GenerateBarcodeActivity.class).putExtra(Constant.CodeType.class.getSimpleName(), Constant.CodeType.QrCode));
                break;

            case R.id.action_image_scan:
                startActivity(new Intent(getApplicationContext(), ScanImageActivity.class));
                break;

            case R.id.action_setting:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                break;

            case R.id.action_export_data:
                startActivity(new Intent(getApplicationContext(), ExportActivity.class));
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.img_flash)
    public void onViewClicked() {
        if (isFlashOn) {
            isFlashOn = false;
            imgFlash.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
            scanner.setTorchOff();
        } else {
            isFlashOn = true;
            imgFlash.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash));
            scanner.setTorchOn();

        }
    }

    @Override
    public void barcodeResult(BarcodeResult result) {
        scanner.pause();
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra(Constant.StaticVariable.Result, new ScannerData(result.toString()));
        intent.putExtra(Constant.StaticVariable.SaveCodeData, true);
        startActivity(intent);

    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        scanner.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scanner.pause();
    }


}
