package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entitity.Acessorio;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
	
	public List<Acessorio> findByNome(String nome);
	

	


}
