package br.senai.sp.restaguide.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.restaguide.model.Administrador;
import br.senai.sp.restaguide.repository.adiminRepository;

@Controller
public class adimController {
	@Autowired
	private adiminRepository repository;
	@RequestMapping("foradim")
	public String salvaradim(Model model) {
		return "adim/form";
		
		
	}
	@RequestMapping(value = "salvaradim", method = RequestMethod.POST)
	public String salvaradim( @Valid Administrador adim, BindingResult resut,RedirectAttributes attr) {
		if(resut.hasErrors()) {
			attr.addFlashAttribute("MensagemErro","verifique os campos...");
			return"redirect:foradim";
		}
		try {
			repository.save(adim);
			attr.addFlashAttribute("mensagemSucesso","Adminstrador cadastrado com sucesso Id:"+ adim.getId());
			return "redirect:foradim";
			
		} catch (Exception e) {
			// TODO: handle exception
			attr.addFlashAttribute("MensagemErro","Houve um erro ao cadastrar o Adminstrador"+e.getMessage());
		}
		return "redirect:foradim";
		
		
	}
	

}
