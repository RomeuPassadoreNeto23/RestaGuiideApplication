package br.senai.sp.restaguide.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.restaguide.annotation.Privado;
import br.senai.sp.restaguide.annotation.Publico;
import br.senai.sp.restaguide.model.Avaliacao;
import br.senai.sp.restaguide.repository.AvaliacaoRepository;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoRest {
	@Autowired
	private AvaliacaoRepository repository;

	@Privado
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Avaliacao> CriarAvaliacao(@RequestBody Avaliacao avaliacao) {
		repository.save(avaliacao);
		return ResponseEntity.created(URI.create("/avaliacao/" + avaliacao.getId())).body(avaliacao);
	}

	@Publico
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Avaliacao getAvalicao(@PathVariable("id") Long id) {
		return repository.findById(id).get();
	}

	@Publico
	@RequestMapping(value = "/restaurante/{id}", method = RequestMethod.GET)
	public List<Avaliacao> listraRestau(@PathVariable("id") Long id) {
		return repository.findByRestauranteId(id);
	}

}
