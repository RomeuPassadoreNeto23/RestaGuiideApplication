package br.senai.sp.restaguide.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.restaguide.model.Usuario;
import br.senai.sp.restaguide.repository.UsuarioRepositotory;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepositotory repositotory;
	@RequestMapping("listarUsuario/{totalPages}/{page}")
	public String Listra(Model model,@PathVariable("page") int page,@PathVariable("totalPages") int totalPages) {
		//cria um pageable com 6 elementos por página o
		PageRequest pegeable = PageRequest.of(page-1, totalPages, Sort.by(Sort.Direction.ASC,"nome"));
		// criar apágina atual através do repository
		 Page<Usuario> pagina = repositotory.findAll(pegeable);
		 // descrobri o total páginas
		  totalPages = pagina.getTotalPages();
		 //criar uma lista de inteiros para 
		 List<Integer> pageNumbers = new ArrayList<Integer>();
		 //preencher a lista as 
		 for(int i = 0; i < totalPages;i++) {
			 pageNumbers.add(i+1);
		 }
		 // adicoi as variaves na model
		 model.addAttribute("usuario",pagina.getContent());
		 model.addAttribute("paginaAtual",page);
		 model.addAttribute("totalPaginas",totalPages);
		 model.addAttribute("numPaginas", pageNumbers);
		 
		return "usuario/lista";
		
	}
	@RequestMapping("buscarUsuario")
	public String buscarPeloNome( String nome, Model model,int Option) {
		if(Option == 1) {
			model.addAttribute("Usuarios",repositotory.buscarPeloNome("%"+nome+"%"));
		}else if (Option == 2) {
			model.addAttribute("Usuarios",repositotory.buscarPeloEmail("%"+nome+"%"));
		}
		
		return "usuario/lista";
		
	}
	
	
	

}
