package id.sheendev.qrbarcodescanner.data.module.database;

import android.os.Parcel;
import android.os.Parcelable;

import id.sheendev.qrbarcodescanner.shared.Constant;
import id.sheendev.qrbarcodescanner.shared.util.DateUtils;
import io.realm.RealmObject;
import lombok.Getter;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

@Getter
public class ScannerData extends RealmObject implements Parcelable{
    String id_data = DateUtils.getTimeStamp();
    String id_user;
    String data;
    String description;
    String date    = DateUtils.getTodayDate("yyyy-MM-dd HH:mm:ss");
    int    storage = Constant.StorageData.Server;

    public ScannerData() {
    }

    public ScannerData(String data) {
        this.data = data;
        this.storage = Constant.StorageData.Local;
    }

    protected ScannerData(Parcel in) {
        id_data = in.readString();
        id_user = in.readString();
        data = in.readString();
        description = in.readString();
        date = in.readString();
        storage = in.readInt();
    }

    public static final Creator<ScannerData> CREATOR = new Creator<ScannerData>() {
        @Override
        public ScannerData createFromParcel(Parcel in) {
            return new ScannerData(in);
        }

        @Override
        public ScannerData[] newArray(int size) {
            return new ScannerData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_data);
        dest.writeString(id_user);
        dest.writeString(data);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeInt(storage);
    }
}
