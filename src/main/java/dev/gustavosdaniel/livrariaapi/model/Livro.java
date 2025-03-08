package dev.gustavosdaniel.livrariaapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data //UMA ANOTAÇÃO DO LOMBOK PARA SUBSTITUIR GAT SET TOSTRING HASCODE
@Entity
@Table(name = "livro")
@EntityListeners(AuditingEntityListener.class) // PARA QUE A ANOTAÇAO DA DATA DE CERTO
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false) // nullable fala que é um campo not nnull
    private String isbn;

    @Column(length = 150, nullable = false) // nullable fala que é um campo not nnull
    private String titulo;

    @Column(nullable = false) // nullable fala que é um campo not nnull
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING) // quando coloca um tipo enum tem que colocar essa anotação nessec caso foi STRING pq está guardando na classe GENEROLLIVRO NOMES DE GENEROS
    @Column(length = 30, nullable = false)
    private GeneroLivro generoLivro;

    @Column() // nullable fala que é um campo not nnull e precision fala que pode ter até 18 algoritimos com até 2 numeros depois da virgula
    private Double preco;

    @ManyToOne // MUITOS LIVROS PARA UM AUTOR
    @JoinColumn() //SERVE PARA MOSTRAR QUE É UM RELACIONAMENTO FK
    private Autor autor;

    @CreatedDate //TODA VES QUE FOI CRIAR A DATA ELE JA VAI COLOCAR AUTOMATICAMENTE
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate // TODA VEZ QUE FOR MODEIFICAR A DATA ELE JA VAI SER MODIFICADO AUTOMATICAMENTE
    @Column(name = "data_atualização", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private Long idUsuario;



    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public GeneroLivro getGenero() {
        return generoLivro;
    }

    public void setGenero(GeneroLivro genero) {
        this.generoLivro = genero;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
