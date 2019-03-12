package id.sheendev.qrbarcodescanner.shared.util;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Rizki Maulana on 05/05/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class Java_AES_Cipher {
    private static String CIPHER_NAME    = "AES/CBC/PKCS5PADDING";
    private static int    CIPHER_KEY_LEN = 16; //128 bits


  /*  public static String encrypt(String data) {
        String key = "0000000000000000";
        String iv  = "0000000000000000";
        try {
            if (key.length() < Java_AES_Cipher.CIPHER_KEY_LEN) {
                int numPad = Java_AES_Cipher.CIPHER_KEY_LEN - key.length();

                for (int i = 0; i < numPad; i++) {
                    key += "0";
                }

            } else if (key.length() > Java_AES_Cipher.CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }


            IvParameterSpec initVector = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec   skeySpec   = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(Java_AES_Cipher.CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

            byte[] encryptedData = cipher.doFinal((data.getBytes()));

            String base64_EncryptedData = Base64.encodeToString(encryptedData, Base64.DEFAULT);
            String base64_IV            = Base64.encodeToString(iv.getBytes("UTF-8"), Base64.DEFAULT);

            return base64_EncryptedData + ":" + base64_IV;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }*/

    public static String decrypt(String data) {
        String key = "0000000000000000";
        try {

            String[] parts = data.split(":");

            IvParameterSpec iv       = new IvParameterSpec(Base64.decode(parts[1].getBytes("UTF-8"), Base64.DEFAULT));
            SecretKeySpec   skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(Java_AES_Cipher.CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decodedEncryptedData = Base64.decode(parts[0].getBytes("UTF-8"), Base64.DEFAULT);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}


