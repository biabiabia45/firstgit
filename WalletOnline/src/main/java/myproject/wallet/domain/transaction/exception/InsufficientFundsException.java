package myproject.wallet.domain.transaction.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException (String message) {
        super(message);
    }
}
