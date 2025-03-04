package dev.gustavosdaniel.livrariaapi.controller;

import dev.gustavosdaniel.livrariaapi.dto.AutorDTO;
import dev.gustavosdaniel.livrariaapi.model.Autor;
import dev.gustavosdaniel.livrariaapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    public final AutorService autorService; // INJELÇAO DE DEPENDENCIA

    public AutorController(AutorService autorService) { // INJELÇAO DE DEPENDENCIA
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autorDTO) { // ResponseEntity É UMA CLASSE QUE REPRESENTA UMA RESPOSTA  NESSE CASO ELE VAI RETORNAR COMO RESPOTA O BOJETO CRIADO EM FORMA DE ID;URL DARIA CERTO TBM SE TIVESSE COLOCADO VOID
        Autor autorEntidade = autorDTO.mapearParaAutor(); // PEGUEI LA NO DTO
        autorService.salvar(autorEntidade); // SALVANDO O AUTORENTIDADE QUE CONTEM APENAS AQUELAS INFORMAÇÕES (NOME, DATANACIMENTO, NACIONALIDADE)

        // ELE VAI RETORNAR UM URL COM O ID DO OBJETO(AUTOR) CRIADO
        URI local = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(local).build();
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

}
