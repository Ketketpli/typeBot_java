package com.katz.typeBot.exceptions;

public class JsonProcessingException extends RuntimeException{

    public JsonProcessingException(){
        super("Json inválido");
    }

    public JsonProcessingException(String message){
        super(message);
    }
}
