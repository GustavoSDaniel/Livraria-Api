package dev.gustavosdaniel.livrariaapi.dto;

import dev.gustavosdaniel.livrariaapi.model.GeneroLivro;

import java.time.LocalDate;

//QUANDO VC QUISER APENAS ALGUMASA INFORMAÇÕES DA SUA ENTIDADE É A CAMADA DE REPRESENTAÇÃO NESSE CASO ELE REEPRESENTA A ENTIDADE AUTOR
// ELE TAMBEM SERVE PARA FAZER VALIDAÇÕES DA REGRA DE NEGOCIO
public record CadastroLivroDTO(
        String isnb,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        Double preco,
        Long idAutor
    ) { // AQUI SÃO AS INFORMAÇÕES QUE EU PRECISO PARA O CADASTRO DO LIVRO

}
