package fr.dlyprod.ecommerce.exceptions.article;

public class PhotoFormatException extends RuntimeException {
    private String message;

    public PhotoFormatException(String message) {
        this.message = message;
    }

    public String getPhoto() {
        return message;
    }

    public void setPhoto(String message) {
        this.message = message;
    }
}
