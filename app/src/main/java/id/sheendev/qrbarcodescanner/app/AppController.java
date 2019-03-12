package id.sheendev.qrbarcodescanner.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;

import id.sheendev.qrbarcodescanner.R;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Rizki Maulana on 11/27/17.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class AppController extends Application {
    public static final String TAG = AppController.class
            .getSimpleName();

    private static AppController mInstance;
    private static Gson          gson;


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name(Realm.DEFAULT_REALM_NAME)
                .build();
        Realm.setDefaultConfiguration(config);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Brown-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        MobileAds.initialize(this, getString(R.string.ads_app_id));


    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

}
