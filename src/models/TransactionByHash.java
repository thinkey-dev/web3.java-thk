package models;

/**
 * Created by thk on 6/15/19.
 */
public class TransactionByHash {

    private String chainId="";
    private String hash="";

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


}
