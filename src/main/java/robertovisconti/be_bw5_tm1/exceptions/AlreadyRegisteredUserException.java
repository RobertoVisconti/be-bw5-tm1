package robertovisconti.be_bw5_tm1.exceptions;

public class AlreadyRegisteredUserException extends RuntimeException {
    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
