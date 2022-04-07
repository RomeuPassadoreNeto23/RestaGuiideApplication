package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.Restaurante;

public interface RestauranteRepository  extends PagingAndSortingRepository<Restaurante, Long>{
 

}
