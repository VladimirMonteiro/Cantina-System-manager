package com.outercode.Cantina.EB.controllers.exceptions;


import java.io.Serial;
import java.io.Serializable;
import java.util.Set;


public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private Set<String> errors;

    public StandardError() {
    }

    public StandardError(int statusCode, Set<String> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }
}