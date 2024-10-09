package Exceptions;
// Simple exception
public class NotInStockException extends Exception {
    public NotInStockException(String message) {
        super(message);
    }
}
