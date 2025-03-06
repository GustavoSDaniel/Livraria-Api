package dev.gustavosdaniel.livrariaapi.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{

    public OperacaoNaoPermitidaException(String message) {// ESSE CONTRUTOR É DA CLASSE RuntimeException
        super(message);
    }
}
