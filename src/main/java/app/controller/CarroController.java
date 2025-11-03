package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entitity.Carro;
import app.service.CarroService;

//@Controller
@RestController
@RequestMapping("/api/carro")
//@CrossOrigin("*") // tambem poderia ser assim @CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200", 
             allowedHeaders = "*",
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
             RequestMethod.DELETE, RequestMethod.OPTIONS})

public class CarroController {
	
	@Autowired 
	private CarroService carroService;
	
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Carro carro) {
		
		try {
			
			System.out.println("Carro recebido no backend: " + carro);
	        System.out.println("Marca recebida: " + carro.getMarca());
	        if (carro.getMarca() != null) {
	            System.out.println("ID da marca recebida: " + carro.getMarca().getId());
	        }
			
			
			String mensagem = this.carroService.save(carro);
			return new ResponseEntity<String>(mensagem,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao salvar", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
	    try {
	        String mensagem = this.carroService.update(id, carroAtualizado);
	        return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<String>("Deu algo errado ao atualizar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Carro>> listAll() {
		try {
			List<Carro> lista = this.carroService.listAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Long id) {
		try {
			Carro carro = this.carroService.findById(id);
			return new ResponseEntity<Carro>(carro,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{idCarro}")
	public ResponseEntity<String> delete(@PathVariable long idCarro) {
		try {
			String mensagem = this.carroService.delete(idCarro);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu este erro: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Carro>> findByNome(@RequestParam String nome) {
		try {
			List<Carro> lista = this.carroService.findByNome(nome);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findAcimaAno")
	public ResponseEntity<List<Carro>> findAcimaAno(@RequestParam int ano) {
		try {
			List<Carro> lista = this.carroService.findAcimaAno(ano);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findByMarca")
	public ResponseEntity<List<Carro>> findByMarca(@RequestParam long idMarca)  {
		try {
			List<Carro> lista = this.carroService.findByMarca(idMarca);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}


