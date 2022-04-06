package br.senai.sp.restaguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.restaguide.repository.TipoRestauranteRepository;

@Controller
public class RestauranteController {
	@Autowired
	private TipoRestauranteRepository respTipo;
	@RequestMapping("formRestaurante")
	public String form(Model model) {
		model.addAttribute("tipos" ,respTipo.findAll());
		return "restaurante/form";
	}
	

}
