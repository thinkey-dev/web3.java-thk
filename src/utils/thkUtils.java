package utils;

import models.vo.Transaction;

import java.math.BigInteger;

import static utils.Sign.recoverFromSignature;

/**
 * Created by thk on 6/18/19.
 */
public class thkUtils {
   public static String privateKey = "0x4c0883a69102937d6231471b5dbb6204fe5129617082792ae468d01a3f362318";



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


    /**
     * 生成签名函数
     *
     * */
    public   static  String CreateSig(ECKeyPair keyPair,String  from, String  to,String  chainId,String  fromChainId,String  toChainId,String Nonce,String  value,String  input)
    {
        if (to.length()>2){
            to =to.substring(2,to.length());
        }
        if (input.length()>2){
            input =input.substring(2,input.length());
        }
        if (from.length()>2){
            from =from.substring(2,from.length());
        }

        String jionstr=chainId+from+to+Nonce+value+input;
        byte[] messageHash;
        messageHash = Hash.sha3(jionstr.getBytes());
        // System.out.println("messageHash:  "+messageHash);

        ECDSASignature sig = keyPair.sign(messageHash);
        // Now we have to work backwards to figure out the recId needed to recover the signature.
        int recId = -1;
        for (int i = 0; i < 4; i++) {
            BigInteger k = recoverFromSignature(i, sig, messageHash);
            System.out.println("k.index: "+i+ " "+ k);
            if (k != null && k.equals(keyPair.getPublicKey())) {
                recId = i;
                break;
            }
        }
        if (recId == -1) {
            throw new RuntimeException(
                    "Could not construct a recoverable key. Are your credentials valid?");
        }
        int headerByte = recId + 27;
        byte v = (byte) headerByte;
        byte[] r = Numeric.toBytesPadded(sig.r, 32);
        byte[] s = Numeric.toBytesPadded(sig.s, 32);
        Sign.SignatureData    signatureData ;
        signatureData=new Sign.SignatureData(v, r, s);
        String Rstr=Numeric.toHexString(r);
        String Sstr=Numeric.toHexString(s);
        String sigData=Rstr+Sstr.replace("0x","");
        if (v==0){
            sigData=sigData+"1b";
        }else{
            sigData=sigData+"1c";
        }
        return   sigData;
    }
    /**
     * 生成签名函数
     *
     * */
    public   static  String CreateSig(ECKeyPair keyPair, Transaction info)
    {
        String to=info.getTo();
        String input=info.getInput();
        String from=info.getFrom();
        if (to.length()>2){
            to =to.substring(2,to.length());
        }
        if (input.length()>2){
            input =input.substring(2,input.length());
        }
        if (from.length()>2){
            from =from.substring(2,from.length());
        }

        String jionstr=info.getChainId()+from+to+info.getNonce()+info.getValue()+input;
        byte[] messageHash;
        messageHash = Hash.sha3(jionstr.getBytes());
        // System.out.println("messageHash:  "+messageHash);

        ECDSASignature sig = keyPair.sign(messageHash);
        // Now we have to work backwards to figure out the recId needed to recover the signature.
        int recId = -1;
        for (int i = 0; i < 4; i++) {
            BigInteger k = recoverFromSignature(i, sig, messageHash);
            System.out.println("k.index: "+i+ " "+ k);
            if (k != null && k.equals(keyPair.getPublicKey())) {
                recId = i;
                break;
            }
        }
        if (recId == -1) {
            throw new RuntimeException(
                    "Could not construct a recoverable key. Are your credentials valid?");
        }
        int headerByte = recId + 27;
        byte v = (byte) headerByte;
        byte[] r = Numeric.toBytesPadded(sig.r, 32);
        byte[] s = Numeric.toBytesPadded(sig.s, 32);
        Sign.SignatureData    signatureData ;
        signatureData=new Sign.SignatureData(v, r, s);
        String Rstr=Numeric.toHexString(r);
        String Sstr=Numeric.toHexString(s);
        String sigData=Rstr+Sstr.replace("0x","");
        if (v==0){
            sigData=sigData+"1b";
        }else{
            sigData=sigData+"1c";
        }
        return   sigData;
    }
}