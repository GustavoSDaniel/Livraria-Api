package dev.gustavosdaniel.livrariaapi.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) { // O CODIGO DO STATUS QUE DEU ERRO, MENSAGEM DE ERRO E UMA LISTA DO CAMPO AONDE ESTA OS ERROS

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of()); // RESPOSTA DE ERRO PADRAO "BAD_REQUEST" O VALÇUE SERVE PARA RETORNAR O NUMERO DO ERRO NO CASO O (404)
    }

    public static ErroResposta conflito(String mensagem) { // EXEMPLO DE ERRO DE CONFLITO QUANDO TENTA CRIAR UM AUTOR JA EXISTENTE
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
