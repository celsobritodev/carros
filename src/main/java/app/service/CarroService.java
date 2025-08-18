package app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entitity.Carro;
import app.repository.CarroRepository;

@Service
public class CarroService {
	
	
	@Autowired
	private CarroRepository carroRepository;
	
	
	public String save(Carro carro) {
		this.carroRepository.save(carro);
		return "Carro salvo com sucesso: ";
	}
	
   	
   public Carro findById(long id) {
		Optional<Carro> carro = this.carroRepository.findById(id);
		return carro.get();
	
   }	

}