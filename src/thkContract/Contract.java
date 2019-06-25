package thkContract;

import com.alibaba.fastjson.JSON;
import models.vo.Transaction;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import utils.ECKeyPair;
import utils.thkUtils;
import web3.Thk;

import java.util.List;
import java.util.Map;

import static thkContract.FunctionEncoder.buildMethodId;
import static thkContract.FunctionEncoder.buildMethodSignature;

/**
 * Created by thk on 6/19/19.
 */
public class Contract {

    //发布合约
    public static Map  Deploy(Thk thk ,Transaction info, String binContent, List<Type> parameters){
        String result=FunctionEncoder.encodeConstructor(parameters);
        System.out.println("pac:"+result);
        String fastr="";
        if (result.length()>2){
            fastr =result.substring(2);
        }

        if (fastr.contains("0x")){
            result=result.substring(2,fastr.length());
        }

        String input=binContent+result;
        String faInput=input.substring(2);

        if (!faInput.contains("0x")){
            input="0x"+input;
        }


        info.setInput(input);;

        //通过私钥获取公钥
        String pub= thkUtils.GetPublicKey();

        //获取ecKeyPair 用于生成签名
        ECKeyPair ecKeyPair=thkUtils.GetECKeyPair();

        String getSig=thkUtils.CreateSig(ecKeyPair,info.getFrom(),info.getTo(), info.getChainId(),info.getFromChainId(),info.getToChainId(),info.getNonce(),info.getValue(),info.getInput());

        info.setPub(pub);
        info.setSig(getSig);


       // Map Result=new Thk().SendTx(info.getChainId(),info.getFromChainId(),info.getToChainId()
       //         ,info.getSig(),info.getPub(),info.getFrom(),info.getTo(), info.getNonce(),info.getValue(),info.getInput());

        Map Result=thk.SendTx(info);
        return Result;
    }

    //需要共识且修改数据状态的合约调用
    public static Map  Send(Thk thk,Transaction info, Function function)
    {
        String input= FunctionEncoder.encode(function);
        info.setInput(input);;

        //通过私钥获取公钥
        String pub= thkUtils.GetPublicKey();
        //获取ecKeyPair 用于生成签名
        ECKeyPair ecKeyPair=thkUtils.GetECKeyPair();
        String getSig=thkUtils.CreateSig(ecKeyPair,info.getFrom(),info.getTo(), info.getChainId(),info.getFromChainId(),info.getToChainId(),info.getNonce(),info.getValue(),info.getInput());
        info.setPub(pub);
        info.setSig(getSig);

//        Map Result=new Thk().SendTx(info.getChainId(),info.getFromChainId(),info.getToChainId()
//                ,info.getSig(),info.getPub(),info.getFrom(),info.getTo(), info.getNonce(),info.getValue(),info.getInput(),info.getExpireHeight());

        Map Result=thk.SendTx(info);
        return Result;

    }

    //从本地节点获取数据的合约调用
    public  static Map Call(Thk thk,Transaction info, Function function)
    {
        String input= FunctionEncoder.encode(function);
        info.setInput(input);
        //Map map=new Thk().CallTransaction(info.getChainId(),info.getFrom(),info.getTo(),info.getNonce(),info.getValue(),info.getInput());
        Map map=thk.CallTransaction(info);
        return  map;
    }
}
