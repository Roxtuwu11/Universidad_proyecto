package com.ibm.academia.apirest.ApiSpring.exceptions;

public class NotFoundException extends RuntimeException
{
    public NotFoundException(String message)
    {
        super(message);
    }

}
