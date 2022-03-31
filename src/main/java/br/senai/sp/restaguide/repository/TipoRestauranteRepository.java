package br.senai.sp.restaguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.TipoRestaurante;

public interface TipoRestauranteRepository  extends PagingAndSortingRepository<TipoRestaurante,Long>{

}
