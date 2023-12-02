package fr.dlyprod.ecommerce.exceptions;

public class CategoryException extends RuntimeException {
    private Long id;

    public CategoryException(Long id) {
        this.id = id;
    }

    public CategoryException(String message) {
        super(message);
    }

    public ErrorResponse reject() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("item");

        return errorResponse;
    }

    public String getMessageID(){
        return "L'ID "+ this.id +"recherché n'existe pas";
    }

}
