package id.sheendev.qrbarcodescanner.app.module.base;

import com.google.gson.Gson;

import id.sheendev.qrbarcodescanner.app.AppController;
import id.sheendev.qrbarcodescanner.domain.repository.database.LocalDb;

/**
 * Created by Rizki Maulana on 31/03/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public abstract class BasePresenter<V extends BaseView> {
    protected LocalDb localDb = LocalDb.getInstance();
    protected Gson    gson    = AppController.getGson();
    protected String  TAG     = getClass().getSimpleName();
    protected V       view;


    public void setView(V view) {
        this.view = view;
    }

    protected V getView() {
        return view;
    }

    public void showIntsAds() {
        getView().onAdsIntShow("");
    }

    public void showBannerAds() {
        getView().onAdsBannerShow("");
    }


}
