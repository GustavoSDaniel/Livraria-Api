package dev.gustavosdaniel.livrariaapi.dto;

import dev.gustavosdaniel.livrariaapi.model.GeneroLivro;

import java.time.LocalDate;

//QUANDO VC QUISER APENAS ALGUMASA INFORMAÇÕES DA SUA ENTIDADE É A CAMADA DE REPRESENTAÇÃO NESSE CASO ELE REEPRESENTA A ENTIDADE AUTOR
// ELE TAMBEM SERVE PARA FAZER VALIDAÇÕES DA REGRA DE NEGOCIO
public record PesquisaLivroDTO(
        Long idLivro,
        String isnb,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        Double preco,
        AutorDTO autorDTO // AUTOR DTO PQ EU PRECISO SO DAS INFORMAÇÕES DISPONIVEIS NO AUTOR DTO
    ) { //ESSES SÃO OS CAMPOS QUE EU VOU PODER UTILIZAR PARA PESQUISAR POR LIVRO


}
