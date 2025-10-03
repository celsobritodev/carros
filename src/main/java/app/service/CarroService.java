package app.service;

import java.util.List;
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


   public String delete(long idCarro) {
		try {
			// Verifica se o carro existe antes de deletar
			if (carroRepository.existsById(idCarro)) {
				carroRepository.deleteById(idCarro);
				return "Carro deletado com sucesso!";
			} else {
				return "Carro não encontrado!";
			}
		} catch (Exception e) {
			return "Erro ao deletar carro: " + e.getMessage();
		}
	}
   
   
   

   public String update(Long id, Carro carroAtualizado) {
		try {
			// Busca o carro existente pelo ID
			Optional<Carro> carroOptional = carroRepository.findById(id);
			
			if (carroOptional.isPresent()) {
				Carro carroExistente = carroOptional.get();
				
				// Atualiza os campos do carro existente com os novos valores
				carroExistente.setMarca(carroAtualizado.getMarca());
				carroExistente.setModelo(carroAtualizado.getModelo());
				
				// Adicione outros campos conforme necessário
				
				// Salva as alterações
				carroRepository.save(carroExistente);
				return "Carro atualizado com sucesso!";
			} else {
				return "Carro não encontrado!";
			}
		} catch (Exception e) {
			return "Erro ao atualizar carro: " + e.getMessage();
		}
	}


   public List<Carro> listAll() {
		// Retorna todos os carros do banco de dados
		return carroRepository.findAll();
	}
   
   
}