package id.sheendev.qrbarcodescanner.app.module.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * Created by Rizki Maulana on 31/03/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected final String TAG = getClass().getSimpleName();
    private ProgressDialog progressDialog;

    protected P presenter;



    @Override
    public void showProgressDialog() {
        if (getActivity() == null) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Harap tunggu ...");
        }
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (getActivity() == null) return;

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        if (getActivity() == null) return;

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onErrorConnectivity() {
        if (getActivity() == null) return;

        if (progressDialog != null) {
            dismissProgressDialog();
        }
        showToast("Tidak dapat memproses permintaan Anda, silakan coba beberapa saat lagi.");
    }

    @Override
    public Activity getContext() {
        return getActivity();
    }

}
