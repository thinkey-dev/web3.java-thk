package thkContract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import models.vo.Transaction;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import utils.ECKeyPair;
import utils.FilesUtils;
import utils.Hash;
import utils.thkUtils;
import web3.Thk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
* 合约测试
*
* */
public class Contract_Test {
    // solc 下载地址：https://github.com/ethereum/solidity/releases/tag/v0.4.25
    // 用 Greeter.so 生成 发合约需要的  abi 和 bin文件
    // solc Greeter.sol  --bin --abi --optimize -o ./output/





    public  static  void  main(String[] args){

        //设置服务器地址
        Thk web3=new Thk();

        web3.setUrl("http://test.thinkey.xyz");


        Transaction info=new Transaction();

        //示例
        info.setFrom("0x2c7536e3605d9c16a7a3d7b1898e529396a65c23");
        //示例私钥
        thkUtils.setPrivateKey("0x4c0883a69102937d6231471b5dbb6204fe5129617082792ae468d01a3f362318");


//        info.setFrom("0x8f8e70f0a554cd5987783ebd0f3dabfa500898b7");
//        thkUtils.setPrivateKey("0x5027156baafa0758473862ca54acd4690f0426ab267186b4324661b474576989");


        String providerAddr="0x8f8e70f0a554cd5987783ebd0f3dabfa500898b7";
        String providerPrivateKey="0x5027156baafa0758473862ca54acd4690f0426ab267186b4324661b474576989";

        System.out.println("Contract Test......");

        String chainId22="2";
        info.setChainId("2");
        info.setFromChainId("2");
        info.setToChainId("2");
        info.setValue("0");
        info.setInput("");
        info.setPub(thkUtils.GetPublicKey());


        String getnotnce=  web3.GetNonce(info.getChainId(),info.getFrom())+"";
        info.setNonce(getnotnce);
        info.setTo("");

        String binContent= FilesUtils.GetResourcesFile("Greeter.bin");
        try{
            //发布合约
            Map result= Contract.Deploy(web3,info,binContent, Collections.emptyList());
            System.out.println("sendtx result :" +result);
            Thread.sleep(5000);    //延时5秒

            String Txhash="";
            if (result.containsKey("TXhash")){
                System.out.println("TXhash:"+result.get("TXhash"));
                Txhash=result.get("TXhash").toString();
            }else{
                System.err.println("TXhash is empty !!");
            }
            //根据返回的Hash查询交易
            Map resultx=  web3.GetTransactionByHash(chainId22,Txhash);

            //返回结果获取合约地址
            if (resultx.containsKey("contractAddress")){
                info.setTo(resultx.get("contractAddress").toString());
            }else{
                System.err.println("contractAddress is empty.....");
            }


            getnotnce=  web3.GetNonce(info.getChainId(),info.getFrom())+"";
            info.setNonce(getnotnce);
            Function function1 = new Function("setGreeting", Arrays.asList(new Utf8String("Greetings!")),  Collections.emptyList());
            Map resultSend= Contract.Send(web3,info,function1);
            System.out.println("resultSend : "+resultSend);
            Thread.sleep(5000);    //延时5秒
            Txhash="";
            if (resultSend.containsKey("TXhash")){
                System.out.println("TXhash:"+resultSend.get("TXhash"));
                Txhash=resultSend.get("TXhash").toString();
            }
            Map resultx1=  web3.GetTransactionByHash(chainId22,Txhash);
            System.out.println(resultx1);


            Function function = new Function("greet", Arrays.asList(),Arrays.asList(new TypeReference<DynamicArray<Utf8String>>() { }));

            Map resultCall= Contract.Call(web3,info,function);
            System.out.println(resultCall);
            List<Type> utf8Strings = FunctionReturnDecoder.decode(
                    resultCall.get("out").toString(),
                    function.getOutputParameters());
            //out Greetings!!
            //System.out.println("output...:"+utf8Strings);

            for (Type t:utf8Strings) {
                System.out.println("output...:"+t.getValue().toString());
            }

        }catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
    }

}
