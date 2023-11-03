package fr.dlyprod.ecommerce.exceptions.article;

public class VideoFormatException extends RuntimeException {
    private String message;

    public VideoFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
