package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entitity.Carro;
import app.entitity.Marca;
import app.repository.CarroRepository;
import app.repository.MarcaRepository; // IMPORTANTE: Adicionar este import

@Service
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired // INJETAR O MarcaRepository
    private MarcaRepository marcaRepository;
    
    public String save(Carro carro) {
        try {
            // Se o carro veio com uma marca que tem ID, busca a marca completa no banco
            if (carro.getMarca() != null && carro.getMarca().getId() != null) {
                Optional<Marca> marcaExistente = marcaRepository.findById(carro.getMarca().getId());
                if (marcaExistente.isPresent()) {
                    // Substitui a marca (que só tem ID) pela marca completa do banco
                    carro.setMarca(marcaExistente.get());
                } else {
                    return "Marca não encontrada com ID: " + carro.getMarca().getId();
                }
            }
            
         // CORREÇÃO: Garantir que a lista de acessórios não seja null
            if (carro.getAcessorios() == null) {
                carro.setAcessorios(new ArrayList<>());
            }
            
            this.carroRepository.save(carro);
            return "Carro salvo com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar carro: " + e.getMessage();
        }
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
    
    public List<Carro> findByNome(String nome) {
        return this.carroRepository.findByNome(nome);
    }
    
    public List<Carro> findByMarca(long idMarca) {
        Marca marca = new Marca();
        marca.setId(idMarca);
        return this.carroRepository.findByMarca(marca);
    }
    
    public List<Carro> findAcimaAno(int ano) {
        return this.carroRepository.findAcimaAno(ano);
    }

    public String update(Long id, Carro carroAtualizado) {
        try {
            // Busca o carro existente pelo ID
            Optional<Carro> carroOptional = carroRepository.findById(id);
            
            if (carroOptional.isPresent()) {
                Carro carroExistente = carroOptional.get();
                
                // Atualiza os campos do carro existente com os novos valores
                carroExistente.setNome(carroAtualizado.getNome());
                carroExistente.setModelo(carroAtualizado.getModelo());
                carroExistente.setAno(carroAtualizado.getAno());
                carroExistente.setAnoFabricacao(carroAtualizado.getAnoFabricacao());
                
                // ATUALIZA A MARCA: Se veio uma marca com ID, busca a marca completa
                if (carroAtualizado.getMarca() != null && carroAtualizado.getMarca().getId() != null) {
                    Optional<Marca> marcaExistente = marcaRepository.findById(carroAtualizado.getMarca().getId());
                    if (marcaExistente.isPresent()) {
                        // Usa a marca COMPLETA do banco
                        carroExistente.setMarca(marcaExistente.get());
                    } else {
                        return "Marca não encontrada com ID: " + carroAtualizado.getMarca().getId();
                    }
                } else {
                    // Se não veio marca, remove a associação
                    carroExistente.setMarca(null);
                }
                
                
             // CORREÇÃO: ATUALIZA OS ACESSÓRIOS
                if (carroAtualizado.getAcessorios() != null) {
                    // Substitui a lista de acessórios existente pela nova lista
                    carroExistente.setAcessorios(carroAtualizado.getAcessorios());
                } else {
                    // Se veio null, limpa os acessórios
                    carroExistente.setAcessorios(new ArrayList<>());
                }
                
                
                
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