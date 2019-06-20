import com.alibaba.fastjson.JSONArray;
import example.MetaCoin;
import io.reactivex.Flowable;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.*;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.protocol.core.methods.response.ShhPost;
import org.web3j.protocol.websocket.events.LogNotification;
import org.web3j.protocol.websocket.events.NewHeadsNotification;
import utils.*;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;
import static utils.Sign.recoverFromSignature;

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

        String sig=thkUtils.CreateSig(ecKeyPair,from,to,chainId2,fromChainId,toChainId,nonce,value,input);

        Map mapresult=web3.SendTx(chainId22,fromChainId,toChainId,sig,pub,from,to,nonce,value,input,ExpireHeight);
        System.out.println(mapresult);


        //8. 获取链结构信息
        JSONArray result_ChainInfo=web3.GetChainInfo();
        System.out.println(result_ChainInfo);


        //9. 获取委员会成员
        String epoch="1";
        JSONArray result_CommitteeInfo=web3.GetCommittee(chainId3,epoch);
        System.out.println(result_CommitteeInfo);


        //10  从合约中读取本地节点的数据
        String  chainId_10="2";
        String  from_10="0x0000000000000000000000000000000000000000";
        String  to_10="0x0e50cea0402d2a396b0db1c5d08155bd219cc52e";
        String  nonce_10="15";
        String  value_10="0";
        String  input_10="0xdfc02018";
        Map calltrResult=web3.CallTransaction(chainId_10,from_10,to_10,nonce_10,value_10,input_10);
        System.out.println(calltrResult);


    }


}
