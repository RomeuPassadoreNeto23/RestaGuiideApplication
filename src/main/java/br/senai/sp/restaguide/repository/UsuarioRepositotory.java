package br.senai.sp.restaguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.Usuario;

public interface UsuarioRepositotory extends PagingAndSortingRepository<Usuario, Long> {

}
