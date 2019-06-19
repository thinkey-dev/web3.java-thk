import com.alibaba.fastjson.JSONArray;
import utils.*;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static utils.Sign.recoverFromSignature;

public class Main {

    //私钥
    static String privateKey = "0x4c0883a69102937d6231471b5dbb6204fe5129617082792ae468d01a3f362318";
    public static void main(String[] args) {
        System.out.println("web3 Test....");



        web3.Thk web3=new web3.Thk();


        String  chainId2="2";
        String  chainId3="3";



        // 1. 获取对应账户金额

        //举例参数： chainId=2   address="0x2c7536e3605d9c16a7a3d7b1898e529396a65c23"

        String chainId="2";
        String address="0x2c7536e3605d9c16a7a3d7b1898e529396a65c23";
        Map map=web3.GetAccount(chainId,address);
        System.out.println(map);



        // 2. 获取交易详情

        //举例参数： chainId="3"  hash="0xb298a034848ea3fccf421824c9bc42d1525994843fcda67fd01ca66f16128ebe";

        chainId="3";

        String hash="0xb298a034848ea3fccf421824c9bc42d1525994843fcda67fd01ca66f16128ebe";
        Map maptx=web3.GetTransactionByHash(chainId,hash);
        System.out.println(maptx);


        // 3. 获取链详情

        //举例参数： chainId="2"

        chainId="2";
        Map mapstas=web3.GetStats(chainId);
        System.out.println(mapstas);


        // 4. 获取指定账户在对应链上一定高度范围内的交易信息

        //举例参数：
        String  chainIdtxs="2";
        String  address_gettx="0x2c7536e3605d9c16a7a3d7b1898e529396a65c23";
        String  startHeight="50";
        String  endHeight="100";
        JSONArray arr=web3.GetTransactions(chainIdtxs,address_gettx,startHeight,endHeight);
        System.out.println(arr);



        // 5. 获取对应块信息
        // 举例参数：

        String  height="30";
        Map mapHeightr=web3.GetBlockHeader(chainId2,height);
        System.out.println(mapHeightr);




        // 6. 获取对应交易
        // 举例参数：
        String  height84="84";
        String  page="1";
        String  size="10";

        Map maptxs=web3.GetBlockTxs(chainId2,height84,page,size);
        System.out.println(maptxs);



        // 7. 发送一笔交易
        // 举例参数：
        String chainId22="2";
        String fromChainId="2";
        String toChainId="2";
        //使用签名方法

        //String sig="0x3c0c75b4dea8c8335475d462bd12dae9e746e3532c6a6b2791cafca565c6610a429fb7260e2f3c64b8e6eb090ee123db700ed2c5f0a4d9a314152f721f0a847101";

        String pub="0x044e3b81af9c2234cad09d679ce6035ed1392347ce64ce405f5dcd36228a25de6e47fd35c4215d1edf53e6f83de344615ce719bdb0fd878f6ed76f06dd277956de";
        String from="0x2c7536e3605d9c16a7a3d7b1898e529396a65c23";
        String to="0x6ea0fefc17c877c7a4b0f139728ed39dc134a967";
        String nonce="33";
        String value="2333";
        String input="";
        int ExpireHeight=0;

        //通过私钥获取公钥
        pub=thkUtils.GetPublicKey();
        //获取ecKeyPair 用于生成签名
        ECKeyPair ecKeyPair=thkUtils.GetECKeyPair();

        String sig=CreateSig(ecKeyPair,from,to,chainId2,fromChainId,toChainId,nonce,value,input);

        Map mapresult=web3.SendTx(chainId22,fromChainId,toChainId,sig,pub,from,to,nonce,value,input,ExpireHeight);
        System.out.println(mapresult);


        //8. 获取链结构信息
        JSONArray result_ChainInfo=web3.GetChainInfo();
        System.out.println(result_ChainInfo);


        //9. 获取委员会成员
        String epoch="1";
        JSONArray result_CommitteeInfo=web3.GetCommittee(chainId3,epoch);
        System.out.println(result_CommitteeInfo);


    }




    /**
     *
     * 根据私钥生成公钥
     * */
    private static   String GetPublicKey(){
        ECKeyPair keyPair = null;

        //String publicKey="0x044e3b81af9c2234cad09d679ce6035ed1392347ce64ce405f5dcd36228a25de6e47fd35c4215d1edf53e6f83de344615ce719bdb0fd878f6ed76f06dd277956de";

        System.out.println(Numeric.hexStringToByteArray(privateKey).toString());
        try {
            keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(privateKey));
        }catch (Exception ex)
        {
            System.err.println(ex);
        }

        BigInteger publicKey1 =keyPair.getPublicKey();
        String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey1);

        String publicKeyHexstr=publicKeyHex;
        publicKeyHex=publicKeyHex.replace("0x","0x04");

        System.out.println("publicKeyHex="+publicKeyHex);
        return   publicKeyHex;
    }


    /**
     * 生成签名函数
     *
     * */
    private  static  String CreateSig(ECKeyPair keyPair,String  from, String  to,String  chainId,String  fromChainId,String  toChainId,String Nonce,String  value,String  input)
    {
//        ECKeyPair keyPair = null;
//        String privateKey = "0x4c0883a69102937d6231471b5dbb6204fe5129617082792ae468d01a3f362318";
//        System.out.println(Numeric.hexStringToByteArray(privateKey).toString());
//        try {
//            keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(privateKey));
//        }catch (Exception ex)
//        {
//            System.err.println(ex);
//        }
//
//        BigInteger publicKey1 =keyPair.getPublicKey();
//        String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey1);
//
//        String publicKeyHexstr=publicKeyHex;
//        publicKeyHex=publicKeyHex.replace("0x","0x04");
//
//        System.out.println("publicKeyHex="+publicKeyHex);

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
}
