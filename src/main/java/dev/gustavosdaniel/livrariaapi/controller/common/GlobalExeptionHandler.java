package dev.gustavosdaniel.livrariaapi.controller.common;

import dev.gustavosdaniel.livrariaapi.dto.ErroCampo;
import dev.gustavosdaniel.livrariaapi.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // CAPTURA EXCESSOES E DA UMA RESPOSTA "REST"
public class GlobalExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // ESSA ANOTAÇÃO QUE CAPTURA O ERRO E JOGA ALI NO METODO
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // ELE SERVE PARA MAPEAR O RETORNO DA RESPOSTA (FALA QUAL É O CODIGO DE ERRO DA RESPOSTA) E ELE SEMPRE VAI RETORNAR ESSA RESPOSTA QUE FOI DADO NO PARAMETRO
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors(); // ELE RETORNA OS CAMPOS QUE DERAM ERRO
        List<ErroCampo> listaDeErros = fieldErrors
                .stream()
                .map(fieldError -> new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList()); // ESSE COMANDO GIGANTE VAI MAPEAR PARA UMA LISTA OS ERROS QUE DEU
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validacão.",
                listaDeErros); // AQUI ELE VAI RETORNAR O STATUS DO ERRO E A MENSAGEM DO ERRO
    }
}
