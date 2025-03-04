package dev.gustavosdaniel.livrariaapi.service;


import dev.gustavosdaniel.livrariaapi.model.Autor;
import dev.gustavosdaniel.livrariaapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    public final AutorRepository autorRepository; //INJESAO DE DEPENDECIA

    public AutorService(AutorRepository autorRepository) {   //INJESAO DE DEPENDECIA
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {  // SALVANDO AUTOR
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(Long id) { // OPTIONAL PQ PODE TER OU NAO O ID
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisarAutor(String nome, String nacionalidade) {

       if (nome != null && nacionalidade != null) {
           return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
       }

        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }
}
