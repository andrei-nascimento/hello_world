package com.generation.blogpessoal.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //Indica que esse objeto abaixo será uma tabela no banco de dados
@Table(name = "tb_postagens") //Dá um nome para tabela a ser criada
public class Postagem {

	//Indica que o id da tabela será uma chave primária
	@Id 
	//Indica que a chave primária será auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Mais completo que NotNull, não deixa inserir espaços em branco
	@NotBlank(message = "O atributo título é obrigatório!") 
	//Define o mínimo e máximo de letras que podem ser inseridos
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres")
	private String titulo;
	
	//Não permite que o Atributo seja Nulo, mas permite que ele contenha apenas Espaços em branco
	@NotNull(message = "O atributo texto é obrigatório!") 
	//Define o mínimo e máximo de letras que podem ser inseridos
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;

	//Indica que a Classe Postagem será o lado N:1 e terá um Objeto da Classe Tema, que no modelo Relacional será a Chave Estrangeira na Tabela tb_postagens (tema_id).
	@ManyToOne
	//Indica que uma parte do JSON será ignorado, ou seja, o Objeto Tema será exibido como um "Sub-Objeto" do Objeto Postagem
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
