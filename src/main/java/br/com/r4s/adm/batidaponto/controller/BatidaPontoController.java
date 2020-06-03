package br.com.r4s.adm.batidaponto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.dominio.BatidaPontoRequest;
import br.com.r4s.adm.batidaponto.service.BatidaPontoService;
import br.com.r4s.adm.colaborador.dominio.Colaborador;
import br.com.r4s.adm.colaborador.repository.ColaboradorRepository;

@RestController
@RequestMapping("/pontos")
public class BatidaPontoController {
	
	@Autowired
	private BatidaPontoService service;
	
	@Autowired
	private ColaboradorRepository repository;
	
	@GetMapping("/batida")
	public String teste() {
		return "OK";
	}
	
	@PostMapping("/batida")
	public ResponseEntity<BatidaPonto> cadastrar(@RequestBody BatidaPontoRequest batidaPonto) {
		
		Date horaBatida = new Date(batidaPonto.getHoraBatida());
		
		Colaborador colaborador = repository.findById(batidaPonto.getColaborador().getId()).get();
		
		BatidaPonto ponto = BatidaPonto.builder()
									   .id(batidaPonto.getId())
									   .colaborador(colaborador)
									   .horaBatida(horaBatida)
									   .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarBatidaSimples(ponto));
	}
}
