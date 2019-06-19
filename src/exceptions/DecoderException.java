package exceptions;

/**
 * Created by thk on 6/18/19.
 */
public class DecoderException extends IllegalStateException {
    private Throwable cause;

    DecoderException(String var1, Throwable var2) {
        super(var1);
        this.cause = var2;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
