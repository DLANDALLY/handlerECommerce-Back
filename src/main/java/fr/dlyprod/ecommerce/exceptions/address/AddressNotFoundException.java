package fr.dlyprod.ecommerce.exceptions.address;

import fr.dlyprod.ecommerce.exceptions.ErrorResponse;

public class AddressNotFoundException extends RuntimeException {
    private String item;

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String item) {
        this.item = item;
    }

    public ErrorResponse reject() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("item");

        return errorResponse;
    }
}
