package it.epicode.w6d5.exception;

public class WrongMatchException extends RuntimeException{
    public WrongMatchException(String msg){
        super(msg);
    }
}
