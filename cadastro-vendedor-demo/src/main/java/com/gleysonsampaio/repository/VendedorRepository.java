package com.gleysonsampaio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gleysonsampaio.model.Vendedor;

@Repository
public interface VendedorRepository  extends JpaRepository<Vendedor, Long>{
	
	@Query("SELECT v from Vendedor v WHERE v.nome like %?1% ")
	public List<Vendedor> findByNome(String nome);

}
