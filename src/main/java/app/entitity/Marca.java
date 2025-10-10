package app.entitity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Marca {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	
	// uma marca pode ter muitos carros
	// um carro pertence a uma marca
	@OneToMany(mappedBy="marca") // o mappedBy deve ser o nome do atributo da classe Carro que referencia a Marca
	//@JoinColumn(name="marca_id") // ← JoinColumn é redundante aqui! CAUSA UM ERRO
	//@JsonManagedReference// evita o loop infinito
	@JsonIgnoreProperties("marca")  // ← Evita loop
	private List<Carro> carros;
	
	
	

}
