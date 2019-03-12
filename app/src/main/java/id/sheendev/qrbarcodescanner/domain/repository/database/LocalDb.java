package id.sheendev.qrbarcodescanner.domain.repository.database;

import java.util.List;

import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import io.realm.Realm;

/**
 * Created by Rizki Maulana on 31/03/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class LocalDb {
    private static LocalDb instance;
    private        Realm   mLocalDb;
    private String TAG = getClass().getSimpleName();

    public static LocalDb getInstance() {
        if (instance == null) {
            instance = new LocalDb();
        }
        return instance;
    }

    private LocalDb() {
        mLocalDb = Realm.getDefaultInstance();
    }

    public void saveCodeData(ScannerData codeData) {
        mLocalDb.executeTransaction(realm -> realm.insert(codeData));
    }


    public void deleteAllData() {
        mLocalDb.executeTransaction(realm -> {
            mLocalDb.where(ScannerData.class).findAll().deleteAllFromRealm();
        });
    }


    public void deleteScannerData(ScannerData scannerData) {
        mLocalDb.executeTransaction(realm -> {
            realm.where(ScannerData.class).equalTo("id_data", scannerData.getId_data()).findFirst().deleteFromRealm();
        });
    }

    public List<ScannerData> getAllCodeData() {
        return mLocalDb.copyFromRealm(mLocalDb.where(ScannerData.class).findAll());
    }

    public void updateScannerData(List<ScannerData> scannerData) {
        mLocalDb.executeTransaction(realm -> {
            realm.where(ScannerData.class).findAll().deleteAllFromRealm();
            realm.insert(scannerData);
        });
    }


}
