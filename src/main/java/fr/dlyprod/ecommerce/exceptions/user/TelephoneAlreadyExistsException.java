package fr.dlyprod.ecommerce.exceptions.user;

import fr.dlyprod.ecommerce.exceptions.ErrorResponse;

public class TelephoneAlreadyExistsException extends RuntimeException {

    private String telephone;
    public TelephoneAlreadyExistsException() {
    }

    public TelephoneAlreadyExistsException(String telephone) {
        this.telephone = telephone;
    }

    //TODO refactor Design Pattern Builder
    public ErrorResponse reject() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("email");

        return errorResponse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
