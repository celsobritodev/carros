package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entitity.Acessorio;
import app.repository.AcessorioRepository;


@Service
public class AcessorioService {
	
	@Autowired
	private AcessorioRepository acessorioRepository;
	
	
	public String save(Acessorio acessorio) {
		this.acessorioRepository.save(acessorio);
		return "Acessorio salvo com sucesso: "+acessorio.getNome();
	}
	
	 public Acessorio findById(long id) {
			Optional<Acessorio> acessorio = this.acessorioRepository.findById(id);
			return acessorio.get();
		
	   }
	 
	   public List<Acessorio> findByNome(String nome) {
		   return this.acessorioRepository.findByNome(nome);
	   }
	   

   public String delete(long idAcessorio) {
		try {
			// Verifica se a marca existe antes de deletar
			if (acessorioRepository.existsById(idAcessorio)) {
				acessorioRepository.deleteById(idAcessorio);
				return "Acessorio deletado com sucesso!";
			} else {
				return "Acessorio não encontrado!";
			}
		} catch (Exception e) {
			return "Erro ao deletar Acessório: " + e.getMessage();
		}
	}
   
  
   
   

   public String update(Long id, Acessorio acessorioAtualizado) {
		try {
			// Busca o acessorio existente pelo ID
			Optional<Acessorio> acessorioOptional = acessorioRepository.findById(id);
			
			if (acessorioOptional.isPresent()) {
				Acessorio acessorioExistente = acessorioOptional.get();
				
				// Atualiza os campos do acessorio existente com os novos valores
				acessorioExistente.setNome(acessorioAtualizado.getNome());
			
				// Salva as alterações
				acessorioRepository.save(acessorioExistente);
				return "Acessorio atualizado com sucesso!";
			} else {
				return "Acessórico não encontrado!";
			}
		} catch (Exception e) {
			return "Erro ao atualizar Acessorio: " + e.getMessage();
		}
	}


   public List<Acessorio> listAll() {
		// Retorna todos os acessorios do banco de dados
		return acessorioRepository.findAll();
	}

}
