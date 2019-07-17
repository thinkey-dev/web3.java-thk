package models.vo;

/**
 * Created by thk on 7/17/19.
 */
public class MakeCCCExistenceProof {
    private  String   chainId;
    private  String   from;
    private  String   to;
    private  String   fromChainId;
    private  String   toChainId;
    private  String   value;
    private  String   expireheight;
    private  String   nonce;

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpireheight() {
        return expireheight;
    }

    public void setExpireheight(String expireheight) {
        this.expireheight = expireheight;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }



}
