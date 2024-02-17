package it.epicode.w6d5.exception;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String msg){
        super(msg);
    }
}
