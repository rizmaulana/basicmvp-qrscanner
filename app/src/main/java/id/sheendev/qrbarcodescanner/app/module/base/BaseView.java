package id.sheendev.qrbarcodescanner.app.module.base;

import android.app.Activity;

/**
 * Created by Rizki Maulana on 31/03/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public interface BaseView {

    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String message);

    void onErrorConnectivity();

    Activity getContext();

    void onAdsIntShow(String adsId);

    void onAdsBannerShow(String adsId);


}
