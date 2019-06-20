import com.alibaba.fastjson.JSONArray;
import models.vo.Transaction;
import utils.*;
import java.util.Map;


public class Main {

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


//        String from="";
//        String to="";
//        String nonce="33";
//        String value="2333";
//        String input="";
//        int ExpireHeight=0;

        String pub="";
        //通过私钥获取公钥
        pub=thkUtils.GetPublicKey();
        //获取ecKeyPair 用于生成签名
        ECKeyPair ecKeyPair=thkUtils.GetECKeyPair();

        Transaction transaction=new Transaction();
        transaction.setChainId("2");
        transaction.setFrom("0x2c7536e3605d9c16a7a3d7b1898e529396a65c23");
        transaction.setTo("0x6ea0fefc17c877c7a4b0f139728ed39dc134a967");
        transaction.setToChainId("2");
        transaction.setFromChainId("2");
        transaction.setNonce("33"); //可自动获取
        transaction.setValue("2333");
        transaction.setInput("");
        transaction.setPub(pub);


//      String sig=thkUtils.CreateSig(ecKeyPair,from,to,chainId2,fromChainId,toChainId,nonce,value,input);
//      Map mapresult=web3.SendTx(chainId22,fromChainId,toChainId,sig,pub,from,to,nonce,value,input);
        String sig=thkUtils.CreateSig(ecKeyPair,transaction);

        transaction.setSig(sig);
        Map mapresult=web3.SendTx(transaction);

        System.out.println(mapresult);


        //8. 获取链结构信息
        JSONArray result_ChainInfo=web3.GetChainInfo();
        System.out.println(result_ChainInfo);

        //9. 获取委员会成员
        String epoch="1";
        JSONArray result_CommitteeInfo=web3.GetCommittee(chainId3,epoch);
        System.out.println(result_CommitteeInfo);

        //10  从合约中读取本地节点的数据
        Transaction info_call=new Transaction();
        info_call.setChainId("2");
        info_call.setFrom("0x0000000000000000000000000000000000000000");
        info_call.setTo("0x0e50cea0402d2a396b0db1c5d08155bd219cc52e");
        info_call.setNonce("15");
        info_call.setValue("0");
        info_call.setInput("0xdfc02018");
        info_call.setFromChainId("2");
        info_call.setToChainId("2");
        Map calltrResult=web3.CallTransaction(info_call);
        System.out.println(calltrResult);

        //11. 发送合约
        //    详见 thkContract->Contract_Test

    }


}
