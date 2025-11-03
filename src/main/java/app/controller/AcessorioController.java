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

import app.entitity.Acessorio;
import app.service.AcessorioService;


@RestController
@RequestMapping("/api/acessorio")
//@CrossOrigin("*") // tambem poderia ser assim @CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200", 
             allowedHeaders = "*",
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
             RequestMethod.DELETE, RequestMethod.OPTIONS})


public class AcessorioController {
	
	@Autowired 
	private AcessorioService acessorioService;
	
	
	// tanto ADMIN quanto USER podem salvar
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Acessorio acessorio) {
		
		try {
			String mensagem = this.acessorioService.save(acessorio);
			return new ResponseEntity<String>(mensagem,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao salvar", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Acessorio acessorioAtualizado) {
	    try {
	        String mensagem = this.acessorioService.update(id, acessorioAtualizado);
	        return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<String>("Deu algo errado ao atualizar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Acessorio>> listAll() {
		try {
			List<Acessorio> lista = this.acessorioService.listAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Acessorio> findById(@PathVariable Long id) {
		try {
			Acessorio acessorio = this.acessorioService.findById(id);
			return new ResponseEntity<Acessorio>(acessorio,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{idAcessorio}")
	public ResponseEntity<String> delete(@PathVariable long idAcessorio) {
		try {
			String mensagem = this.acessorioService.delete(idAcessorio);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu este erro: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Acessorio>> findByNome(@RequestParam String nome) {
		try {
			List<Acessorio> lista = this.acessorioService.findByNome(nome);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}

}
