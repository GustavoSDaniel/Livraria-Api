package dev.gustavosdaniel.livrariaapi.dto;

import dev.gustavosdaniel.livrariaapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


//QUANDO VC QUISER APENAS ALGUMASA INFORMAÇÕES DA SUA ENTIDADE É A CAMADA DE REPRESENTAÇÃO NESSE CASO ELE REEPRESENTA A ENTIDADE AUTOR
// ELE TAMBEM SERVE PARA FAZER VALIDAÇÕES DA REGRA DE NEGOCIO
public record AutorDTO(Long id,
                       @NotBlank(message = "Campo obrigatorio")// O @NotBlank SERVE PARA QUE A STRING NÃO VENHA VAZIA OU NULA
                       @Size(min = 2, max = 100, message = "Campo fora do tamahno padrão")
                       String nome,
                       @Past(message = "Não pode ser uma data futura") // ESSA ANOTAÇÃO EVITA QUE UMA DATA FUTURA SEJA COLOCADA NO CAMPO DATADENASCIMENTO
                       @NotNull(message = "Campo obrigatorio") //@NotNull PARA QUE O CAMPO NÃO VENHA NULO
                       LocalDate dataNascimento,
                       @Size(min = 2, max = 100, message = "Campo fora do tamahno padrão")
                       @NotBlank(message = "Campo obrigatorio") // O @NotBlank SERVE PARA QUE A STRING NÃO VENHA VAZIA OU NULA
                       String nacionalidade
) {

    public Autor mapearParaAutor() { //TRANFERINDO ESSAS INFORMAÇÕES PARA A ENTIDADE AUTOR

        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
