package com.generation.bsm.bsm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bsm-generation")
public class BsmController {

	@GetMapping
	public String bsm() {
		return "Mentalidades: Orientação ao Futuro, Responsabilidade Pessoal, Mentalidade de Crescimento e Persistência." 
				+ " Habilidades: Trabalho em Equipe, Atenção aos Detalhes, Proatividade e Comunicação.";
	}
	
}
