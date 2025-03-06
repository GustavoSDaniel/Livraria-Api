package dev.gustavosdaniel.livrariaapi.validator;

import dev.gustavosdaniel.livrariaapi.exceptions.RegistroDuplicadoExeption;
import dev.gustavosdaniel.livrariaapi.model.Autor;
import dev.gustavosdaniel.livrariaapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // PARA QUE ESSA CLASSE POSSA SER GERENCIADO PELO SPRING
public class AutorValidator {
    private AutorRepository autorRepository; // INJETOR O REPOSITORY PARA VERIFICAR SE NO BANCO DE DADOS JA TA CADASTRADO O AUTOR

    public AutorValidator(AutorRepository autorRepository) { //INJETOR O REPOSITORY
        this.autorRepository = autorRepository;
    }

    public void validarAutor(Autor autor) { // AQUI ELE VAI VALIDAR O AUTOR
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoExeption("Autor já cadastrado !");
        }
    }

    private boolean existeAutorCadastrado (Autor autor) { // LOGICA PARA VER SE EXISTE AUTOR CADASTRADO
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndNacionalidadeAndDataNascimento(autor.getNome(), autor.getNacionalidade(), autor.getDataNascimento()); // CASO O AUTOR JA EXISTIR ELE VAI RETORNAR O AUTORR QUE VC TA TENTANDO CADASTRAR

        if (autor.getId() == null) { // LOGICA PARA CADASTRAR O AUTOR: SE O AUTOR QUE EU ESTOU CADASTRANDO NÃO TEM ID ELE VAI SER CADASTRADO
            return autorEncontrado.isPresent(); // SE O ID NÃO FOR NULO VAI RETORNAR  throw new RegistroDuplicadoExeption("Autor já cadastrado !"); PQ JA EXISTE UM AUTOR
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent(); // SE EXISTE AUTOR CADASTRADO É QUANDO ELE NÃO TEM O MESMO ID DO QUE EU ESTOU TENTANDO CADASTRAR E TEM QUE EXISTIR UM AUTOR ENCONTRADO QUE NÃO SEJA ELE
    }
}
