package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entitity.Marca;
import app.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	
	public String save(Marca marca) {
		this.marcaRepository.save(marca);
		return "Marca salva com sucesso: ";
	}
	
   	
   public Marca findById(long id) {
		Optional<Marca> marca = this.marcaRepository.findById(id);
		return marca.get();
	
   }


   public String delete(long idMarca) {
		try {
			// Verifica se a marca existe antes de deletar
			if (marcaRepository.existsById(idMarca)) {
				marcaRepository.deleteById(idMarca);
				return "Marca deletada com sucesso!";
			} else {
				return "Marca não encontrada!";
			}
		} catch (Exception e) {
			return "Erro ao deletar marca: " + e.getMessage();
		}
	}
   
   
   public List<Marca> findByNome(String nome) {
	   return this.marcaRepository.findByNome(nome);
   }
   
   
   

   public String update(Long id, Marca marcaAtualizada) {
		try {
			// Busca o carro existente pelo ID
			Optional<Marca> marcaOptional = marcaRepository.findById(id);
			
			if (marcaOptional.isPresent()) {
				Marca marcaExistente = marcaOptional.get();
				
				// Atualiza os campos do carro existente com os novos valores
				marcaExistente.setNome(marcaAtualizada.getNome());
				marcaExistente.setCnpj(marcaAtualizada.getCnpj());
				// Salva as alterações
				marcaRepository.save(marcaExistente);
				return "Marca atualizada com sucesso!";
			} else {
				return "Marca não encontrado!";
			}
		} catch (Exception e) {
			return "Erro ao atualizar marca: " + e.getMessage();
		}
	}


   public List<Marca> listAll() {
		// Retorna todos os carros do banco de dados
		return marcaRepository.findAll();
	}

}
