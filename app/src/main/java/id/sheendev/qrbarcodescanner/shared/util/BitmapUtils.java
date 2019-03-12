package id.sheendev.qrbarcodescanner.shared.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import id.sheendev.qrbarcodescanner.shared.Constant;

/**
 * Created by Rizki Maulana on 21/04/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class BitmapUtils {
    public static File takeScreenshoot(String title, View screenView, int width, int height) {
        title = title.replace(" ", "");
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c      = new Canvas(bitmap);
        screenView.draw(c);
        return store(bitmap, title + DateUtils.getStringTimeStamp() + ".jpg");
    }

    private static File store(Bitmap bm, String fileName) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constant.Dir;
        File   dir     = new File(dirPath);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    //  private static Bitmap compressImage(File file) {
     /*   int quality = 25;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            FileInputStream inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("location to save");
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.close();
            long lengthInKb = file.length() / 1024;
            if (lengthInKb > SIZE_LIMIT) {
                compressImage(file,  (quality / 4));
            }

            selectedBitmap.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    //  }
}
