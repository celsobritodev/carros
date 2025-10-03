package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import app.entitity.Carro;
import app.service.CarroService;

@Controller
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
	
}


