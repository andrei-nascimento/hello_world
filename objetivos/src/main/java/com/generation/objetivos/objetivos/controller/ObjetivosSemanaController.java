package com.generation.objetivos.objetivos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/objetivos-semana")
public class ObjetivosSemanaController {

	@GetMapping
	public String objetivosSemana() {
		return "Objetivos de aprendizagem da semana: Estudar mais POO, Banco de Dados e entender a dinâmica do Spring";	
	}
}
