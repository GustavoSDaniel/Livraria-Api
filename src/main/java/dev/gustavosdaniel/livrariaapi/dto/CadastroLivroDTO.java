package dev.gustavosdaniel.livrariaapi.dto;

import dev.gustavosdaniel.livrariaapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;

//QUANDO VC QUISER APENAS ALGUMASA INFORMAÇÕES DA SUA ENTIDADE É A CAMADA DE REPRESENTAÇÃO NESSE CASO ELE REEPRESENTA A ENTIDADE AUTOR
// ELE TAMBEM SERVE PARA FAZER VALIDAÇÕES DA REGRA DE NEGOCIO
public record CadastroLivroDTO(
        @ISBN // PARA FALAR QUE É UM ISBN IGUAL O DO ID
        @NotBlank(message = "Campo Obrigatório") // [E OBRIGATORIO O CAMPO SER PREENCHIDO
        String isbn,
        @NotBlank(message = "Campo Obrigatório") // [E OBRIGATORIO O CAMPO SER PREENCHIDO
        String titulo,
        @NotNull(message = "Campo Obrigatório") // É OBRIGATORIO O CAMPO SER PREENCHIDO POREM COM NÃO É STRING UTILIZA NOTNULL
        @Past(message = "Não pode ser uma data futura") //ESSA ANOTAÇÃO INDICA QUE A DATA NÃO POPDE SER UMA DATA FUTURA DO MOMENTO ATUAL
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        Double preco,
        @NotNull(message = "Campo Obrigatório") // É OBRIGATORIO O CAMPO SER PREENCHIDO POREM COM NÃO É STRING UTILIZA NOTNULL
        Long idAutor
    ) { // AQUI SÃO AS INFORMAÇÕES QUE EU PRECISO PARA O CADASTRO DO LIVRO

}
