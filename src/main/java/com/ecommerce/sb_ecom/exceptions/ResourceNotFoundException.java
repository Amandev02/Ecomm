package com.ecommerce.sb_ecom.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    String resourceNAme;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(String resourceNAme, String field, String fieldName) {
        super(String.format( "%s not found with %s: %s", resourceNAme,field,fieldName));
        this.resourceNAme = resourceNAme;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException( String resourceNAme, String field, Long fieldId) {
        super(String.format( "%s not found with %s: %d", resourceNAme,field,fieldId));
        this.resourceNAme = resourceNAme;
        this.field = field;
        this.fieldId = fieldId;
    }
}
