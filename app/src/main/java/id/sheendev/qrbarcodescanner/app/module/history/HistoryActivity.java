package id.sheendev.qrbarcodescanner.app.module.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.app.module.base.BaseActivity;
import id.sheendev.qrbarcodescanner.app.module.result.ResultActivity;
import id.sheendev.qrbarcodescanner.app.ui.widget.SimpleAlert;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.Constant;

public class HistoryActivity extends BaseActivity<HistoryPresenter> implements HistoryView {

    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.menu_history));

        presenter = new HistoryPresenter();
        presenter.setView(this);
        presenter.setScannerData();
        presenter.showBannerAds();

    }


    @Override
    public void onDataSet(List<ScannerData> codeData) {
        ScannerDataAdapter scannerDataAdapter = new ScannerDataAdapter(codeData);
        scannerDataAdapter.setSetOnItemClickListener(new ScannerDataAdapter.setOnItemClickListener() {
            @Override
            public void onClick(ScannerData codeData) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(Constant.StaticVariable.Result, codeData);
                startActivity(intent);
            }

            @Override
            public void onDeleteClicked(ScannerData codeData) {
                new SimpleAlert(HistoryActivity.this, getString(R.string.msg_delete_data), new SimpleAlert.OnChooser() {
                    @Override
                    public void onOk() {
                        presenter.deleteScannerData(codeData);
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
        });

        recyclerView.setAdapter(scannerDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void updateDataSet() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showRefeshIndicator() {
        swipeLayout.post(() -> {
            swipeLayout.setRefreshing(true);
        });
    }

    @Override
    public void dismissRefreshIndicator() {
        swipeLayout.post(() -> {
            swipeLayout.setRefreshing(false);
        });
    }

}
