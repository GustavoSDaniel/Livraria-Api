package dev.gustavosdaniel.livrariaapi.exceptions;

public class RegistroDuplicadoExeption extends RuntimeException{

    public RegistroDuplicadoExeption(String message) { // ESSE CONsTRUTOR É DA CLASSE RuntimeException
        super(message);
    }
}
