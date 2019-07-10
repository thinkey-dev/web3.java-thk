package web3;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import models.Account;
import models.TransactionByHash;
import models.vo.Transaction;

/**
 * Created by thk on 6/15/19.
 */
public class Thk {


    /**
     * 服务期地址
     *
     * */
    public String Url="";

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }



   /* 获取账户余额*/
   public Map GetAccount(String chainId,String address ){
        //举例

        Account account =new models.Account();


        account.setChainId(chainId);
        account.setAddress(address);
        String jsonObj = JSONObject.toJSONString(account);


        String postJson="{\"method\": \"GetAccount\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
   }

    /* 获取交易详情*/
    public Map GetTransactionByHash(String chainId,String hash ){
        //举例

        TransactionByHash info =new models.TransactionByHash();
        info.setChainId(chainId);
        info.setHash(hash);
        String jsonObj = JSONObject.toJSONString(info);


        String postJson="{\"method\": \"GetTransactionByHash\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    /* 获取链详情*/
    public Map GetStats(String chainId){
        //举例

        Map map=new HashMap();
        map.put("chainId",chainId);
        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetStats\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    /* 根据地址获取指定高度内的交易信息*/
    public JSONArray GetTransactions(String chainId, String address, String startHeight, String endHeight){
        //举例
        Map map=new HashMap();
        map.put("chainId",chainId);
        map.put("address",address);
        map.put("startHeight",startHeight);
        map.put("endHeight",endHeight);
        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetTransactions\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        return JSON.parseArray(result);
    }

    /* 根据高度详情*/
    public Map GetBlockHeader(String chainId, String height){
        //举例
        Map map=new HashMap();
        map.put("chainId",chainId);
        map.put("height",height);

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetBlockHeader\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    //获取对应高度内交易
    public Map GetBlockTxs(String chainId, String height,String page,String size){
        //举例
        Map map=new HashMap();
        map.put("chainId",chainId);
        map.put("height",height);
        map.put("page",page);
        map.put("size",size);
        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetBlockTxs\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    //发送交易
    @Deprecated
    public Map SendTx(String chainId, String fromChainId, String toChainId, String sig, String pub, String from,
                      String to, String nonce, String value,String input){
        //举例
        Map map=new HashMap();
        map.put("chainId",chainId);
        map.put("fromChainId",fromChainId);
        map.put("toChainId",toChainId);
        map.put("sig",sig);
        map.put("pub",pub);
        map.put("from",from);
        map.put("to",to);
        map.put("nonce",nonce);
        map.put("value",value);
        map.put("input",input);
        //map.put("ExpireHeight",ExpireHeight);

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"SendTx\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    //发送交易
    public Map SendTx(Transaction info){
        //举例
        Map map=new HashMap();
        map.put("chainId",info.getChainId());
        map.put("fromChainId",info.getFromChainId());
        map.put("toChainId",info.getToChainId());
        map.put("sig",info.getSig());
        map.put("pub",info.getPub());
        map.put("from",info.getFrom());
        map.put("to",info.getTo());
        map.put("nonce",info.getNonce());
        map.put("value",info.getValue());
        map.put("input",info.getInput());
        //map.put("ExpireHeight",ExpireHeight);

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"SendTx\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    //获取Nonce
    public int GetNonce(String chainId, String address){
        //举例
        Account account =new models.Account();
        account.setChainId(chainId);
        account.setAddress(address);
        String jsonObj = JSONObject.toJSONString(account);
        String postJson="{\"method\": \"GetAccount\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        if (maps.containsKey("nonce")){
            return  (int)maps.get("nonce");
        }
        return 0;
    }

    //保存合约
    public Map SaveContract(String address,String Contract){
        //举例
        Map map=new HashMap();
        map.put("contractaddr",address);
        map.put("contract",Contract);

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"SaveContract\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }

    //获取链结构
    public  JSONArray  GetChainInfo()
    {
        //举例
        Map map=new HashMap();
        map.put("chainIds","[]");

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetChainInfo\",\"params\": "+jsonObj+"}";
        String result=Post(Url+"/chaininfo",postJson);
        return JSON.parseArray(result);
    }

    //获取委员会详情
    public JSONArray GetCommittee(String chainId,String epoch){
        //举例
        Map map=new HashMap();
        map.put("chainId",chainId);
        map.put("epoch",epoch);

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"GetCommittee\",\"params\": "+jsonObj+"}";
        String result=Post(Url+"/chaininfo",postJson);
        return JSON.parseArray(result);
    }

   //从合约中读取本地节点的数据
   @Deprecated
   public Map CallTransaction(String chainId,String from,String  to  ,String nonce, String value,String input){
       //举例
       Map map=new HashMap();
       map.put("chainId",chainId);
       map.put("from",from);
       map.put("to",to);
       map.put("nonce",nonce);
       map.put("value",value);
       map.put("input",input);
       map.put("fromChainId",chainId);
       map.put("toChainId",chainId);

       String jsonObj = JSONObject.toJSONString(map);
       String postJson="{\"method\": \"CallTransaction\",\"params\": "+jsonObj+"}";
       String result=Post(Url,postJson);
       Map maps = (Map) JSON.parse(result);
       return maps;
   }
    //从合约中读取本地节点的数据
    public Map CallTransaction(Transaction info){
        //举例参数
        Map map=new HashMap();
        map.put("chainId",info.getChainId());
        map.put("from",info.getFrom());
        map.put("to",info.getTo());
        map.put("nonce",info.getNonce());
        map.put("value",info.getValue());
        map.put("input",info.getInput());
        map.put("fromChainId",info.getFromChainId());
        map.put("toChainId",info.getToChainId());

        String jsonObj = JSONObject.toJSONString(map);
        String postJson="{\"method\": \"CallTransaction\",\"params\": "+jsonObj+"}";
        String result=Post(Url,postJson);
        Map maps = (Map) JSON.parse(result);
        return maps;
    }



    /**
     * 发送HttpPost请求
     *
     * @param strURL
     *            服务地址
     * @param params
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    private static String Post(String strURL, String params) {
        System.out.println("server:\n"+strURL);
        System.out.println("params:\n"+params+"\n");
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;

            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();

            //如果一定要使用如下方式接收响应数据， 则响应必须为: response.getWriter().print(StringUtils.join("{\"errCode\":\"1\",\"errMsg\":\"", message, "\"}")); 来返回
//            int length = (int) connection.getContentLength();// 获取长度
//            if (length != -1) {
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0;
//                int destPos = 0;
//                while ((readLen = is.read(temp)) > 0) {
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8"); // utf-8编码
//                System.out.println(result);
//                return result;
//            }

            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }



}
