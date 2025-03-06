package dev.gustavosdaniel.livrariaapi.dto;

import dev.gustavosdaniel.livrariaapi.model.Autor;

import java.time.LocalDate;


//QUANDO VC QUISER APENAS ALGUMASA INFORMAÇÕES DA SUA ENTIDADE É A CAMADA DE REPRESENTAÇÃO NESSE CASO ELE REEPRESENTA A ENTIDADE AUTOR
public record AutorDTO(Long id, String nome, LocalDate dataNascimento, String nacionalidade) {

    public Autor mapearParaAutor() { //TRANFERINDO ESSAS INFORMAÇÕES PARA A ENTIDADE AUTOR

        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
