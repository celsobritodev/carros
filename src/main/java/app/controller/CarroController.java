package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entitity.Carro;
import app.service.CarroService;

@Controller
@RequestMapping("/carro")
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
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Long id) {
		try {
			Carro carro = this.carroService.findById(id);
			return new ResponseEntity<Carro>(carro,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}


