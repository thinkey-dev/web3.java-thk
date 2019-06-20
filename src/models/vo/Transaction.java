package models.vo;

/**
 * Created by thk on 6/17/19.
 */
public class Transaction {


    private  String  chainId;
    private  String  fromChainId;
    private  String  toChainId;
    private  String  sig;
    private  String  pub;
    private  String  from;
    private  String  to;
    private  String  nonce;
    private  String  value;
    private  String  input;
    private  int  ExpireHeight;

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getFromChainId() {
        return fromChainId;
    }

    public void setFromChainId(String fromChainId) {
        this.fromChainId = fromChainId;
    }

    public String getToChainId() {
        return toChainId;
    }

    public void setToChainId(String toChainId) {
        this.toChainId = toChainId;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getExpireHeight() {
        return ExpireHeight;
    }

    public void setExpireHeight(int expireHeight) {
        ExpireHeight = expireHeight;
    }


}
