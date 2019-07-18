#Web3-Java

##如何调用

web3.Thk web3=new web3.Thk();

web3.setUrl("http://test.thinkey.xyz");


####举例： 获取账户余额 (各函数调用详见源码)

    String chainId="2";
    String address="0x2c7536e3605d9c16a7a3d7b1898e529396a65c23";
    Map map=web3.GetAccount(chainId,address);
    System.out.println(map);

