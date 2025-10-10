package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import app.entitity.Marca;
import app.service.MarcaService;

@RestController
@RequestMapping("/api/marca")
//@CrossOrigin("*") // tambem poderia ser assim @CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200", 
             allowedHeaders = "*",
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
             RequestMethod.DELETE, RequestMethod.OPTIONS})


public class MarcaController {
	
	@Autowired 
	private MarcaService marcaService;
	
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Marca marca) {
		
		try {
			String mensagem = this.marcaService.save(marca);
			return new ResponseEntity<String>(mensagem,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao salvar", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Marca marcaAtualizada) {
	    try {
	        String mensagem = this.marcaService.update(id, marcaAtualizada);
	        return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<String>("Deu algo errado ao atualizar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Marca>> listAll() {
		try {
			List<Marca> lista = this.marcaService.listAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Marca> findById(@PathVariable Long id) {
		try {
			Marca marca = this.marcaService.findById(id);
			return new ResponseEntity<Marca>(marca,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{idMarca}")
	public ResponseEntity<String> delete(@PathVariable long idMarca) {
		try {
			String mensagem = this.marcaService.delete(idMarca);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu este erro: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Marca>> findByNome(@RequestParam String nome) {
		try {
			List<Marca> lista = this.marcaService.findByNome(nome);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}

}
