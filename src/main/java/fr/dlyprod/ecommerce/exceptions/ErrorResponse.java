package fr.dlyprod.ecommerce.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private int status;
    private String reason;
    private String attribute;
}
