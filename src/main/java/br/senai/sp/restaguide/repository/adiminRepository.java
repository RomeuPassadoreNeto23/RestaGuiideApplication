package br.senai.sp.restaguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.Administrador;

public interface adiminRepository  extends PagingAndSortingRepository<Administrador, Long>{

}
