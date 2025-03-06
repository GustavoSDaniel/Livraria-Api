package dev.gustavosdaniel.livrariaapi.controller;

import dev.gustavosdaniel.livrariaapi.dto.AutorDTO;
import dev.gustavosdaniel.livrariaapi.dto.ErroResposta;
import dev.gustavosdaniel.livrariaapi.exceptions.OperacaoNaoPermitidaException;
import dev.gustavosdaniel.livrariaapi.exceptions.RegistroDuplicadoExeption;
import dev.gustavosdaniel.livrariaapi.model.Autor;
import dev.gustavosdaniel.livrariaapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor // FAZ A INJEÇÃO DE DEPENDENCIA SEM ´PRECISAR FICAR CRIANDO CONSTRUTOR
public class AutorController {

    public final AutorService autorService; // INJELÇAO DE DEPENDENCIA

    // public AutorController(AutorService autorService) { // INJELÇAO DE DEPENDENCIA
    //    this.autorService = autorService;
   // }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autorDTO) { // ResponseEntity É UMA CLASSE QUE REPRESENTA UMA RESPOSTA  NESSE CASO ELE VAI RETORNAR COMO RESPOTA O BOJETO CRIADO EM FORMA DE ID;URL DARIA CERTO TBM SE TIVESSE COLOCADO VOID
        try {
            Autor autorEntidade = autorDTO.mapearParaAutor(); // PEGUEI LA NO DTO
            autorService.salvar(autorEntidade); // SALVANDO O AUTORENTIDADE QUE CONTEM APENAS AQUELAS INFORMAÇÕES (NOME, DATANACIMENTO, NACIONALIDADE)

            // ELE VAI RETORNAR UM URL COM O ID DO OBJETO(AUTOR) CRIADO
            URI local = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

            return ResponseEntity.created(local).build();
        } catch (RegistroDuplicadoExeption exeption) { // QUERO CAPTURAR O ERRO DE REGISTRO DUPLICADO
            ErroResposta erroDTO = ErroResposta.conflito(exeption.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO); // VAI RETORNAR O STATUS DO ERRO E O ERRO DTO QUE É LA DA CLASSE ERRORESPSOTA
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") Long id) { // ISSO INDICA QUE ELE VAI PEGAR A VARIAVEL QUE ESTA MAPEADA NO GETMAPPING NO CASO ID
        Optional<Autor> autorOptional = autorService.obterPorId(id);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                    autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build(); // ERRO 404 DE ID NÃO ENCONTRADO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAutorPorId(@PathVariable("id") Long id) {

        try {
            Optional<Autor> autorDeletadoPorId = autorService.obterPorId(id);

            if (autorDeletadoPorId.isEmpty()) { // SE NÃO TIVER AUTOR
                return ResponseEntity.notFound().build();
            }

            autorService.deletarAutor(autorDeletadoPorId.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) { // O ERRO QUE ELE VAI LANÃR É OPERAÇÃO NAO PERMITIDA
            ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage()); // AQUI VAI PASSAR A RESPOSTA PADRAO QUE É O 400
            return ResponseEntity.status(erroResposta.status()).body(erroResposta); // VAI RETORNAR O STATUS DO ERRO
        }


    }

    @GetMapping  //TOMAR CUIDADO PARA NÃO TER PARAMETROS REPETIDOS
    public ResponseEntity<List<AutorDTO>> pesquisar (@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "nacionalidade", required = false) String nacionalidade) { // RequestParam PARAMETROS DA PESQUIA, , required = false INDICA QUE NÃO É OBRIGATORIO PASSAR ESSES 2 PARAMETROS

        List<Autor> resultado =  autorService.pesquisarAutor(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(autor ->  new AutorDTO( // AQUI ELE VAI MAPEAR CADA RESULTADO
                autor.getId(),autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()) // AQUI ELE VAI TA TRANSFORMANDO ESSES RESULTADOS EM UM AUTORDTO
                ).collect(Collectors.toList()); // AQUI ELE TA TRANSFORMANDO CADA AUTORDTO EM UM ITEM DE LISTA
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAutor(@PathVariable Long id, @RequestBody AutorDTO dto) { // (@PathVariable PARA PEGAR O PARAMETRO ID DO ENDIPOPINT E O @RequestBody PARA MOSTRAR O CORPO DO DTO
        try {
            Optional<Autor> atualizarAutorPorId = autorService.obterPorId(id);

            if (atualizarAutorPorId.isEmpty()) { // SE NÃO TIVER AUTOR (se ESSE ID NÃO EXISTIR)
                return ResponseEntity.notFound().build();
            }

            Autor autor = atualizarAutorPorId.get();
            autor.setNome(dto.nome());
            autor.setNacionalidade(dto.nacionalidade());
            autor.setDataNascimento(dto.dataNascimento());

            autorService.atualizarAutorPorId(autor);

            return ResponseEntity.noContent().build(); // a requisição foi processada com sucesso, mas não há corpo na resposta.
        } catch (RegistroDuplicadoExeption exeption) {
            ErroResposta erroDTO = ErroResposta.conflito(exeption.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO); // VAI RETORNAR O STATUS DO ERRO E O ERRO DTO QUE É LA DA CLASSE ERRORESPSOTA
        }

    }

}
