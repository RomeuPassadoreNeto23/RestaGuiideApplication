package br.senai.sp.restaguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.restaguide.model.Restaurante;
import br.senai.sp.restaguide.repository.RestauranteRepository;
import br.senai.sp.restaguide.repository.TipoRestauranteRepository;

@Controller
public class RestauranteController {
	@Autowired
	private TipoRestauranteRepository respTipo;
	@Autowired
	private RestauranteRepository resopRestau;
	@RequestMapping("formRestaurante")
	public String form(Model model) {
		model.addAttribute("tipos" ,respTipo.findAll());
		return "restaurante/form";
	}
	
	@RequestMapping("SavarRestaurante")
	public String SavarRestaurante(Restaurante restaurante, @RequestParam("filefotos")MultipartFile[]filefotos) {
		System.out.println(filefotos.length);
		resopRestau.save(restaurante);
		return "redirect:formRestaurante";
		
	}
	

}
