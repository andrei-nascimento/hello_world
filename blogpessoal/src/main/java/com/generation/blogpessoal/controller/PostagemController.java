package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

//Indica que é uma classe controller - métodos GET, POST, PUT, DELETE
@RestController
//Usada para implementar o URL/endpoint
@RequestMapping("/postagens") //O endpoint seria o URL
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	//Injeção de dependência ou Transferência de responsabilidade (instancía o que está abaixo)
	@Autowired 
	private PostagemRepository postagemRepository; //chamamos por um apelido(postagemRepository)
	
	//Dá acesso aos Métodos das Classes Tema e TemaController
	@Autowired
	private TemaRepository temaRepository;
	
	//SELECT * FROM tb_postagem;
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() { //List aceita todas as qtds de postagens
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	//SELECT * FROM tb_postagens where id = id;
	@GetMapping("/{id}") //sub-rota ou sub-endpoint
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//SELECT * FROM tb_postagens where titulo like "%titulo%";
	@GetMapping("/titulo/{titulo}") //{} = Variável de caminho(PathVariable)
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) { //O List cria um array para mostrar todos os conteúdos solicitados
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//INSERT INTO tb_postagens (titulo, texto, data) VALUES ("Título", "Texto", CURRENT_TIMESTAMP());
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
		if(temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	//UPDATE tb_postagens SET titulo = "titulo", texto = "texto" WHERE id = id;
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
		if(postagemRepository.existsById(postagem.getId())) {
			
			if(temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//DELETE FROM tb_postagens WHERE id = id;
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id); //Quando se tem apenas duas opções de resposta é usado o Optional(ou acha o id ou não acha)
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		postagemRepository.deleteById(id);
	}
}
