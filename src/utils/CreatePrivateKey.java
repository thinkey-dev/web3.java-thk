package utils;


import static utils.Hash.sha256;
import static utils.SecureRandomUtils.secureRandom;

/**
 * Created by thk on 6/18/19.
 */
public class CreatePrivateKey {


    private static String generateMnemonics() {
        byte[] initialEntropy = new byte[16];
        secureRandom().nextBytes(initialEntropy);
        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
        return mnemonic;
    }

    public static void main(String[] args) {


        String mnemonic = generateMnemonics();
        String password = "123456";

        //如果使用密码可能与大部分钱包不兼容

        byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);

        //test
        //ECKeyPair ecKeyPair = ECKeyPair.create(sha256(seed));
        ECKeyPair ecKeyPair = ECKeyPair.create(Numeric.hexStringToByteArray("0x5027156baafa0758473862ca54acd4690f0426ab267186b4324661b474576989"));

        String priKeyWithPrefix = Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey());
        String pubKeyWithPrefix = Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey());


        String pubKey= Numeric.toHexString(Numeric.toBytesPadded(ecKeyPair.getPublicKey(), 64));
        //根据公钥或者ECKeyPair获取钱包地址

        String address1 = Keys.getAddress(ecKeyPair);
        String address2 = Keys.getAddress(pubKeyWithPrefix);

        
        System.out.println("privatekey:"+priKeyWithPrefix);
        System.out.println("publickey:"+pubKey);   //

        System.out.println("Address:"+address1);
        System.out.println("Address:"+address2);
    }

}
