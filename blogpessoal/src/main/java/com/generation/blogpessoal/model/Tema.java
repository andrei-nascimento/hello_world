package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temass")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	private String descricao;
	
	//O nome do Atributo da Classe Proprietária (Tema), que foi criado na Classe filha Postagem (Tema tema), que será o Objeto de referência na Relação.
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) //Quando um Objeto da Classe Tema for apagado, todos os Objetos da Classe Postagem associados ao Tema apagado ou atualizado, também serão apagados ou atualizados. O Inverso não é verdadeiro.
	@JsonIgnoreProperties("tema") //Impede o looping infinito - Recursividade
	private List<Postagem> postagem; //Prepara a model para receber a lista de postagens
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
