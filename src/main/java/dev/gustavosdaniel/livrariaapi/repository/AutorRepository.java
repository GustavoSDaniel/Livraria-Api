package dev.gustavosdaniel.livrariaapi.repository;

import dev.gustavosdaniel.livrariaapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNome(String nome);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);

    Optional<Autor>findByNomeAndNacionalidadeAndDataNascimento(String nome, String nacionalidade ,LocalDate dataNascimento);

}
