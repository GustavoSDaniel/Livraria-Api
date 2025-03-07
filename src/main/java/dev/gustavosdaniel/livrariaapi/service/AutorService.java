package dev.gustavosdaniel.livrariaapi.service;


import dev.gustavosdaniel.livrariaapi.exceptions.OperacaoNaoPermitidaException;
import dev.gustavosdaniel.livrariaapi.model.Autor;
import dev.gustavosdaniel.livrariaapi.repository.AutorRepository;
import dev.gustavosdaniel.livrariaapi.repository.LivroRepository;
import dev.gustavosdaniel.livrariaapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor // FAZ A INJEÇÃO DE DEPENDENCIA SEM ´PRECISAR FICAR CRIANDO CONSTRUTOR
public class AutorService {

    public final AutorRepository autorRepository; //INJESAO DE DEPENDECIA
    public final AutorValidator autorValidator; //INJESAO DE DEPENDECIA
    public final LivroRepository livroRepository;

  //  public AutorService(AutorRepository autorRepository, AutorValidator autorValidator, LivroRepository livroRepository) {
  //      this.autorRepository = autorRepository;
  //      this.autorValidator = autorValidator;
  //      this.livroRepository = livroRepository;
  //  }

    public Autor salvar(Autor autor) {  // SALVANDO AUTOR
        autorValidator.validarAutor(autor); // VALIDANDO SE JA NÃO EXISTE NENHUM AUTOR COM ESSE CADASTRO
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(Long id) { // OPTIONAL PQ PODE TER OU NAO O ID
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {

        if (autorPossuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um Autor que possui livros cadastrados");
        }

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

    public List<Autor> pesquisarAutorByExample(String nome, String nacionalidade) {
        Autor autor = new Autor();

        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase("id", "dataNascimento", "dataCadastro") //AQUI ELE IGNORA OS CAMPOS QUE NÃO SÃO FEITO PARA PESQUISAR
                .withIgnoreNullValues()//ELE VAI IGNORAR QUALQUER OUTRO VALOR NULO QUE NÃO FOI PREENCHIDO
                .withIgnoreCase() // ELE VAI IGNORAR SE TA EM CAIXA ALTA OU EM MINUSCOLO
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // AQUI ELE VAI BUSCAR O AUTOR MESMO SE A PALAVRA NÃO MESTIVER COMPLETO EX GUS VAI BUSCAR AUTOR QUE TEM ESSAS LETRAS
        Example<Autor> autorExample = Example.of(autor, exampleMatcher);
        return autorRepository.findAll(autorExample); // TEM QUE SER O FINDALL EXAMPLE
    }

    public void atualizarAutorPorId(Autor autor) {
        if (autor.getId() == null) { // VERIFICANDO SE JA TEM ESSE ID PARA QUE POSSA SER ATUALIZADO
            throw new IllegalArgumentException("Para atualizar, é necessaáio que já o autor já esteja salvo na base");
        }
        autorValidator.validarAutor(autor); // VALIDANDO SE O AUTOR PODE SER ATUALIZADO
        autorRepository.save(autor);
    }

    public boolean autorPossuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
