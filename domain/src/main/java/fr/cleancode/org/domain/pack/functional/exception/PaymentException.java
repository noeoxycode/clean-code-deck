package fr.cleancode.org.domain.pack.functional.exception;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }
}