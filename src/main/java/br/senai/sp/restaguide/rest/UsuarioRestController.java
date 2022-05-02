package br.senai.sp.restaguide.rest;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.restaguide.annotation.Privado;
import br.senai.sp.restaguide.annotation.Publico;
import br.senai.sp.restaguide.model.Erro;
import br.senai.sp.restaguide.model.Usuario;
import br.senai.sp.restaguide.repository.UsuarioRepositotory;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
	@Autowired
private UsuarioRepositotory repositotory;
	
	@Publico
	@RequestMapping(value = "",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> criarUsuario(@RequestBody Usuario usuario){
		try {
             
			// savar o usuario no banco de dados
			repositotory.save(usuario);
			// retronar codigo 201 , com url para acesso no location eo usuario
			// no corpo da resposta
			return ResponseEntity.created(URI.create("/" + usuario.getId())).body(usuario);
		} catch (DataIntegrityViolationException e) {

			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Erro de Contraint: registro Duplicado");
			erro.setExcepiton(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Erro ");
			erro.setExcepiton(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	
	
	}
	@Privado
	@RequestMapping(value = "/{id}" ,method =  RequestMethod.GET)
	public  ResponseEntity<Usuario>findUsuario(@PathVariable("id") Long idUsuario){
		Optional<Usuario> usuario = repositotory.findById(idUsuario);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@Privado
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> atulizarUsuario(@RequestBody Usuario usuario,@PathVariable("id") Long id){
		if(id != usuario.getId()) {
			throw new  RuntimeException("id invalido");
		}
		// savar o usuario no banco de dados
		repositotory.save(usuario);
		// criar um cabeçalhao HTTp
		HttpHeaders headers =  new HttpHeaders();
		headers.setLocation(URI.create("/api/usuario/"));
		return  new ResponseEntity<Void>(headers,HttpStatus.OK);
		
	}
	@Privado
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void>ExcluirUsuario( @PathVariable("id")Long idUsuario){
		repositotory.deleteById(idUsuario);
		
		return ResponseEntity.noContent().build();
		
		
	}

}
