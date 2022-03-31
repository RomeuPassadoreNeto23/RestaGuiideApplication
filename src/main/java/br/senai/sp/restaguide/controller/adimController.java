package br.senai.sp.restaguide.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import br.senai.sp.restaguide.repository.adiminRepository;
import br.senai.sp.restaguide.util.HashUtil;

@Controller
public class adimController {
	@Autowired
	private adiminRepository repository;
	@RequestMapping("foradim")
	public String form(Model model) {
		
		return "adim/form";
		
		
	}
	@RequestMapping(value = "salvaradim", method = RequestMethod.POST)
	public String salvaradim( @Valid Administrador adim, BindingResult resut,RedirectAttributes attr) {
		if(resut.hasErrors()) {
			attr.addFlashAttribute("MensagemErro","verifique os campos...");
			return"redirect:foradim";
		}
		boolean alteraca = adim.getId() != null ? true:false;
		if(adim.getSenha().equals(HashUtil.hash256(""))) {
			if(!alteraca) {
				String parte = adim.getEmail().substring(0,adim.getEmail().indexOf("@"));
				adim.setSenha(parte);
			}else {
				// busca a senha atual
				String hash = repository.findById(adim.getId()).get().getSenha();
				adim.setSenhaComHash(hash);
				
			}
			
		}
		try {
			repository.save(adim);
			attr.addFlashAttribute("mensagemSucesso","Adminstrador salvo com sucesso Id:"+ adim.getId());
			return "redirect:foradim";
			
		} catch (Exception e) {
			// TODO: handle exception
			attr.addFlashAttribute("MensagemErro","Houve um erro ao cadastrar o Adminstrador"+e.getMessage());
		}
		return "redirect:foradim";
		
		
	}
	// request mapping para listar , informando a página desejada
	@RequestMapping("listarAdimin/{totalPages}/{page}")
	public String Listra(Model model,@PathVariable("page") int page, @PathVariable("totalPages") int totalPages) {
		//cria um pageable com 6 elementos por página o
		PageRequest pegeable = PageRequest.of(page-1, totalPages, Sort.by(Sort.Direction.ASC,"nome"));
		// criar apágina atual através do repository
		 Page<Administrador> pagina = repository.findAll(pegeable);
		 // descrobri o total páginas
		  totalPages = pagina.getTotalPages();
		 //criar uma lista de inteiros para 
		 List<Integer> pageNumbers = new ArrayList<Integer>();
		 //preencher a lista as 
		 for(int i = 0; i < totalPages;i++) {
			 pageNumbers.add(i+1);
		 }
		 // adicoi as variaves na model
		 model.addAttribute("admins",pagina.getContent());
		 model.addAttribute("paginaAtual",page);
		 model.addAttribute("totalPaginas",totalPages);
		 model.addAttribute("numPaginas", pageNumbers);
		 
		return "adim/lista";
		
	}
	@RequestMapping("excluirAdimins")
	public String excluiradimins(long id) {
		repository.deleteById(id);
		return "redirect:listarAdimin/1";
		
	}
	@RequestMapping("alterarAdimins")
	public String alterarAdimins(Model model ,long id) {
		Administrador adimns =  repository.findById(id).get();
		model.addAttribute("adimins",adimns);
		return "forward:foradim";
	}
	@RequestMapping("buscarAdm")
	public String buscarPeloNome( String nome, Model model,int Option) {
		if(Option == 1) {
			model.addAttribute("admins",repository.buscarPeloNome("%"+nome+"%"));
		}else if (Option == 2) {
			model.addAttribute("admins",repository.buscarPeloEmail("%"+nome+"%"));
		}
		
		return "adim/lista";
		
	}
	

}
