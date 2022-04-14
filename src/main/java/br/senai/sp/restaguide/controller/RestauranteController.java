package br.senai.sp.restaguide.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.restaguide.model.Restaurante;
import br.senai.sp.restaguide.model.TipoRestaurante;
import br.senai.sp.restaguide.repository.RestauranteRepository;
import br.senai.sp.restaguide.repository.TipoRestauranteRepository;
import br.senai.sp.restaguide.util.FirebaseUtil;

@Controller
public class RestauranteController {
	@Autowired
	private TipoRestauranteRepository respTipo;
	@Autowired
	private RestauranteRepository resopRestau;
	@Autowired
	private FirebaseUtil firebaseUtil;
	
	@RequestMapping("formRestaurante")
	public String form(Model model) {
		model.addAttribute("tipos" ,respTipo.findAll());
		return "restaurante/form";
	}
	
	@RequestMapping("SavarRestaurante")
	public String SavarRestaurante(Restaurante restaurante, @RequestParam("filefotos")MultipartFile[]filefotos) {
		String fotos = "";
		for (MultipartFile arquivo:filefotos) {
			if(arquivo.getOriginalFilename().isEmpty()) {
				continue;
			} try {
				 fotos += firebaseUtil.uploadFile(arquivo) +";";;
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		} 
		restaurante.setFotos(fotos);
		resopRestau.save(restaurante);
		return "redirect:formRestaurante";
		
	}
	@RequestMapping("listarRestaurante/{totalPages}/{page}")
	public  String listra(Model model,@PathVariable("page") int page,@PathVariable("totalPages") int totalPages) {
		//cria um pageable com 6 elementos por página o
		PageRequest pegeable = PageRequest.of(page-1, totalPages, Sort.by(Sort.Direction.ASC,"nome"));
		// criar apágina atual através do repository
		 Page<Restaurante> pagina = resopRestau.findAll(pegeable);
		 // descrobri o total páginas
		  totalPages = pagina.getTotalPages();
		 //criar uma lista de inteiros para 
		 List<Integer> pageNumbers = new ArrayList<Integer>();
		 //preencher a lista as 
		 for(int i = 0; i < totalPages;i++) {
			 pageNumbers.add(i+1);
		 }
		 // adicoi as variaves na model
		 model.addAttribute("restaurante",pagina.getContent());
		 model.addAttribute("paginaAtual",page);
		 model.addAttribute("totalPaginas",totalPages);
		 model.addAttribute("numPaginas", pageNumbers);
		 
		 
		 
		 return "restaurante/listar";
	}
	@RequestMapping("alterarRestaurante")
	public String alterarTiposRes(Model model ,long id) {
		Restaurante Res = resopRestau.findById(id).get();
		model.addAttribute("restaurante",Res);
		return "forward:formRestaurante";
	}
	@RequestMapping("excluirFotos")
	public String excluirFotos( Long idRestaurante, int numfoto,Model model) {
		Restaurante rest = resopRestau.findById(idRestaurante).get();
		String fotoUrl = rest.verFotos()[numfoto];
		firebaseUtil.deletar(fotoUrl);
		rest.setFotos(rest.getFotos().replace(fotoUrl + ";","" ));
		resopRestau.save(rest);
		model.addAttribute("restaurante", rest);
		return "forward:formRestaurante";
		
	}
	@RequestMapping("excluirRestaurante")
	public String excluirRestaurante(Long id) {
		Restaurante rest =  resopRestau.findById(id).get();
		if(rest.getFotos().length() > 0) {
			for(String foto : rest.verFotos()) {
				firebaseUtil.deletar(foto);
			}
		}
		resopRestau.delete(rest);
		return"redirect:/listarRestaurante/2/1";
		
	}


	

}
