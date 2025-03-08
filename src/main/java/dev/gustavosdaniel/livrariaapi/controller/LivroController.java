package dev.gustavosdaniel.livrariaapi.controller;

import dev.gustavosdaniel.livrariaapi.dto.CadastroLivroDTO;
import dev.gustavosdaniel.livrariaapi.dto.ErroResposta;
import dev.gustavosdaniel.livrariaapi.exceptions.RegistroDuplicadoExeption;
import dev.gustavosdaniel.livrariaapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor // FAZ A INJEÇÃO DE DEPENDENCIA SEM ´PRECISAR FICAR CRIANDO CONSTRUTOR
public class LivroController {

    private final LivroService livroService; // INJELÇAO DE DEPENDENCIA

    @PostMapping
    public ResponseEntity<Object> cadastrarLivro (@RequestBody @Valid CadastroLivroDTO cadastroLivroDTO) { // @RequestBody PQ PRECISA RETORNAR O CORPO DO CADASTRO, @Valid  VALIDAR O LIVRODTO CERTIFICAR QUE O CADASTRO NÃO ESTEJA SENDO FEITO ERRADO
        try {
            //mapear dto para entidade
            //enviar a entidade para o service vaçidar e salvar na base
            // criar url para acesso dos dados do livro
            //retornar codigo created com header location
            return ResponseEntity.ok(cadastroLivroDTO);
        }catch (RegistroDuplicadoExeption exeption) {
            ErroResposta erroDTO = ErroResposta.conflito(exeption.getMessage()); // MENSAGEM DE ERRO NA CLASSE DE DTO ERRORESPOSTA
            return ResponseEntity.status(erroDTO.status()).body(erroDTO); // VAI RETORNAR O STATUS DO ERRO E O ERRO DTO QUE É LA DA CLASSE ERRORESPSOTA
        }
    }
}
