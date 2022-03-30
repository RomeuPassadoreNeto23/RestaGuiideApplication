package br.senai.sp.restaguide.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.restaguide.model.TipoRestaurante;
import br.senai.sp.restaguide.repository.TipoRestauranteRepository;

@Controller
public class TipoResController {
	@Autowired
	private TipoRestauranteRepository respoRestaurant;
	@RequestMapping("formTipoRes")
	public String form() {
		return "TipoRestaurante/form";
	}
	@RequestMapping(value = "salvarTipoRes", method = RequestMethod.POST)
	public String salvarTipoRes( @Valid TipoRestaurante tipores, BindingResult resut,RedirectAttributes attr) {
		if(resut.hasErrors()) {
			attr.addFlashAttribute("MensagemErro","verifique os campos...");
			return"redirect:formTipoRes";
		}
		try {
			respoRestaurant.save(tipores);
			attr.addFlashAttribute("mensagemSucesso","Adminstrador salvo com sucesso Id:"+ tipores.getId());
			return "redirect:formTipoRes";
			
		} catch (Exception e) {
			// TODO: handle exception
			attr.addFlashAttribute("MensagemErro","Houve um erro ao cadastrar o Adminstrador"+e.getMessage());
		}
		return "redirect:formTipoRes";
		
	}

}
