package dev.gustavosdaniel.livrariaapi.service;

import dev.gustavosdaniel.livrariaapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // para fazer a INJECAO DE DEPENDENCIAS
public class LivroService {

    private final LivroRepository livroRepository;


}
