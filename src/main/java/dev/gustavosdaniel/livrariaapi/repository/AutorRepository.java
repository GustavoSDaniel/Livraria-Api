package dev.gustavosdaniel.livrariaapi.repository;

import dev.gustavosdaniel.livrariaapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepository extends JpaRepository<Autor, Long> {

}
