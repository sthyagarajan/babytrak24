package com.babytrak24.util;
/**
 * 
 * @author sunder
 *  This class was created to be used for Custom Error Message written as part of REST API
 *
 */
public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
