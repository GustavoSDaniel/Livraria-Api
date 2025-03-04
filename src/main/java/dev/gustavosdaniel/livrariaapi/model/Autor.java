package dev.gustavosdaniel.livrariaapi.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class) // PARA QUE A ANOTAÇAO DA DATA DE CERTO
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)  // nullable fala que é um campo not nnull
    private String nome;

    @Column(length = 50, nullable = false) // nullable fala que é um campo not nnull
    private String nacionalidade;

    @Column(name = "data_nascimento", nullable = false)// nullable fala que é um campo not nnull
    private LocalDate dataNascimento;

    @CreatedDate //TODA VES QUE FOI CRIAR A DATA ELE JA VAI COLOCAR AUTOMATICAMENTE
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate // TODA VEZ QUE FOR MODEIFICAR A DATA ELE JA VAI SER MODIFICADO AUTOMATICAMENTE
    @Column(name = "data_atualização", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @OneToMany(mappedBy = "autor") // UM AUTOR PARA VARIOS LIVROS, MAPEADO PELA ENTIDADE AUTOR "autor" ( QUE ESTA NA TABELA DE LIVROS)
    private List<Livro> livros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade + ", dataNascimento="
                + dataNascimento + ", livros=" + livros + "]";
    }
}
