package br.senai.sp.restaguide.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.restaguide.model.Administrador;
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
			attr.addFlashAttribute("mensagemSucesso","Restaurante salvo com sucesso Id:"+ tipores.getId());
			return "redirect:formTipoRes";
			
		} catch (Exception e) {
			// TODO: handle exception
			attr.addFlashAttribute("MensagemErro","Houve um erro ao cadastrar do Restaurante"+e.getMessage());
		}
		return "redirect:formTipoRes";
		
	}
	// request mapping para listar , informando a página desejada
		@RequestMapping("listarTipoRes/{page}")
		public String Listra(Model model,@PathVariable("page") int page) {
			//cria um pageable com 6 elementos por página o
			PageRequest pegeable = PageRequest.of(page-1, 6, Sort.by(Sort.Direction.ASC,"nome"));
			// criar apágina atual através do repository
			 Page<TipoRestaurante> pagina = respoRestaurant.findAll(pegeable);
			 // descrobri o total páginas
			 int totalPages = pagina.getTotalPages();
			 //criar uma lista de inteiros para 
			 List<Integer> pageNumbers = new ArrayList<Integer>();
			 //preencher a lista as 
			 for(int i = 0; i < totalPages;i++) {
				 pageNumbers.add(i+1);
			 }
			 // adicoi as variaves na model
			 model.addAttribute("TipoRes",pagina.getContent());
			 model.addAttribute("paginaAtual",page);
			 model.addAttribute("totalPaginas",totalPages);
			 model.addAttribute("numPaginas", pageNumbers);
			 
			return "TipoRestaurante/lista";
			
		}
		@RequestMapping("excluirTipoRes")
		public String excluiTipoRes(long id) {
			respoRestaurant.deleteById(id);
			return "redirect:listarTipoRes/1";
			
		}
		@RequestMapping("alterarTiposRes")
		public String alterarTiposRes(Model model ,long id) {
			TipoRestaurante TipoRes =  respoRestaurant.findById(id).get();
			model.addAttribute("TipoRes",TipoRes);
			return "forward:formTipoRes";
		}
	

}
