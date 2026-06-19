package com.katz.typeBot.exceptions;

public class PerfilNotFoundException extends RuntimeException{

    public PerfilNotFoundException(){
        super("Perfil não encontrado.");
    }

    public PerfilNotFoundException(String message){
        super(message);
    }
}
