package thkContract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import models.vo.Transaction;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
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
* */
public class Contract_Test {

    public  static  void  main(String[] args){
        String chainId22="2";
        Transaction info=new Transaction();
        info.setFrom("0x2c7536e3605d9c16a7a3d7b1898e529396a65c23");
        info.setChainId("2");
        info.setFromChainId("2");
        info.setToChainId("2");
        info.setValue("0");
        info.setInput("");
        info.setPub(thkUtils.GetPublicKey());
        Thk web3=new Thk();
        String getnotnce=  web3.GetNonce(info.getChainId(),info.getFrom())+"";
        info.setNonce(getnotnce);
        info.setTo("");
        String binContent= FilesUtils.GetResourcesFile("Greeter.bin");
        try{

            Map result= Contract.Deploy(info,binContent, Collections.emptyList());
            System.out.println("sendtx result :" +result);
            Thread.sleep(5000);    //延时5秒

            String Txhash="";
            if (result.containsKey("TXhash")){
                System.out.println("TXhash:"+result.get("TXhash"));
                Txhash=result.get("TXhash").toString();
            }else{
                System.err.println("TXhash is empty !!");
            }

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
            Map resultSend= Contract.Send(info,function1);
            System.out.println("resultSend : "+resultSend);
            Thread.sleep(5000);    //延时5秒
            Txhash="";
            if (resultSend.containsKey("TXhash")){
                System.out.println("TXhash:"+resultSend.get("TXhash"));
                Txhash=resultSend.get("TXhash").toString();
            }
            Map resultx1=  web3.GetTransactionByHash(chainId22,Txhash);
            System.out.println(resultx1);


            Function function = new Function("greet", Arrays.asList(), Arrays.asList(
                    new TypeReference<Utf8String>() { }));

            Map resultCall= Contract.Call(info,function);
            System.out.println(resultCall);
            List<Type> utf8Strings = FunctionReturnDecoder.decode(
                    resultCall.get("out").toString(),
                    function.getOutputParameters());
            //out Greetings!!
            System.out.println("output...:"+utf8Strings);

        }catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
    }

}
