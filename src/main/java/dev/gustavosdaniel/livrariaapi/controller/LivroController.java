package dev.gustavosdaniel.livrariaapi.controller;

import dev.gustavosdaniel.livrariaapi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor // FAZ A INJEÇÃO DE DEPENDENCIA SEM ´PRECISAR FICAR CRIANDO CONSTRUTOR
public class LivroController {

    private final LivroService livroService; // INJELÇAO DE DEPENDENCIA

}
