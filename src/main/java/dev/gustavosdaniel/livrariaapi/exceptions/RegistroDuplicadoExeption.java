package dev.gustavosdaniel.livrariaapi.exceptions;

public class RegistroDuplicadoExeption extends RuntimeException{

    public RegistroDuplicadoExeption(String message) { // ESSE CONTRUTOR É DA CLASSE RuntimeException
        super(message);
    }
}
