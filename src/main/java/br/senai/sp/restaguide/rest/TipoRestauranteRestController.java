package br.senai.sp.restaguide.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.restaguide.model.TipoRestaurante;
import br.senai.sp.restaguide.repository.TipoRestauranteRepository;
@RequestMapping("/api/TipoRestaurante")
@RestController
public class TipoRestauranteRestController {
	@Autowired
	private TipoRestauranteRepository repository;
	@RequestMapping(value = "",method = RequestMethod.GET)
	public Iterable<TipoRestaurante> getTipoRestaurante(){
		return repository.findAll();
	}
	

}
