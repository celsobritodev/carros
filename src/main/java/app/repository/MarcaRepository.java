package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entitity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
	
	public List<Marca> findByNome(String nome);
	
	public List<Marca> findByCnpj(Marca marca);
	


}
