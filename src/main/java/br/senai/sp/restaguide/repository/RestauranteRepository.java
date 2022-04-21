package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.Restaurante;

public interface RestauranteRepository  extends PagingAndSortingRepository<Restaurante, Long>{
	public List<Restaurante> findByTipoId(Long idTipo);
	public List<Restaurante> findByEspacoInfantil(boolean trueOrFalse);
	public List<Restaurante> findByEstado(String estado);
	
 

}
