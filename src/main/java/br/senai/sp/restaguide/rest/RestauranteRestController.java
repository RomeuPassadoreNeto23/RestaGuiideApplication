package br.senai.sp.restaguide.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.restaguide.annotation.Publico;
import br.senai.sp.restaguide.model.Restaurante;
import br.senai.sp.restaguide.repository.RestauranteRepository;
@RequestMapping("/api/restaurante")
@RestController
public class RestauranteRestController {
	@Autowired
	private RestauranteRepository repository;
	@RequestMapping(value = "",method = RequestMethod.GET)
	public  Iterable<Restaurante> getRestaurantes(){
		return  repository.findAll();
	}
	@Publico
	@RequestMapping(value = "/{id}" ,method =  RequestMethod.GET)
	public ResponseEntity<Restaurante> finsRestaurante(@PathVariable("id") Long id){
		Optional<Restaurante> restaurante = repository.findById(id);
		if(restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@Publico
	@RequestMapping(value = "/tipo/{idTipo}",method = RequestMethod.GET)
	public Iterable<Restaurante>finstipores(@PathVariable("idTipo")Long idtipo){
		return repository.findByTipoId(idtipo);
	}
	@Publico
	@RequestMapping(value = "/espaco/{espaso}",method = RequestMethod.GET)
	public  List<Restaurante>finsespacoInfantil( @PathVariable("espaso")boolean trueOrfalse){
		return repository.findByEspacoInfantil(trueOrfalse);
		
		}
	@Publico
	@RequestMapping(value = "/estado/{es}",method = RequestMethod.GET)
	public List<Restaurante>finsestado(@PathVariable("es")String estado){
		return repository.findByEstado(estado);
	}
		
	}


