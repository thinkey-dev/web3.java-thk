package utils;

import java.math.BigInteger;

/**
 * Created by thk on 6/18/19.
 */
public class thkUtils {
    static String privateKey = "0x4c0883a69102937d6231471b5dbb6204fe5129617082792ae468d01a3f362318";



    /**
     * 根据私钥生成公钥
     */
    public static String GetPublicKey() {
        ECKeyPair keyPair = null;
        System.out.println(Numeric.hexStringToByteArray(privateKey).toString());
        try {
            keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(privateKey));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        BigInteger publicKey1 = keyPair.getPublicKey();
        String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey1);
        String publicKeyHexstr = publicKeyHex;
        //注意： 需要替换头
        publicKeyHex = publicKeyHex.replace("0x", "0x04");
        //System.out.println("publicKeyHex=" + publicKeyHex);
        return publicKeyHex;
    }

    /**
     * 获取GetECKeyPair
     */
    public static ECKeyPair GetECKeyPair(){
        ECKeyPair keyPair = null;
        try {
            keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(privateKey));
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
        return keyPair;
    }
}