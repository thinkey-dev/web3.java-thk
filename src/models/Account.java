package models;

/**
 * Created by thk on 6/15/19.
 */

public class Account {


    private String chainId="";
    private String address="";
    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
