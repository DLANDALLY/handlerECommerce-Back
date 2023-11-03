package fr.dlyprod.ecommerce.exceptions.user;

import fr.dlyprod.ecommerce.exceptions.ErrorResponse;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public ErrorResponse reject() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("email");

        return errorResponse;
    }
}
