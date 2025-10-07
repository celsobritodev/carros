package app.entitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private Integer ano;
    
    @NotNull(message = "Nome não pode ser nulo")
	private String nome;
    

    private Integer marca_id;
    
	private String marca;
	private String modelo;

	@Column(name="ano_fabricacao")
	private Integer anoFabricacao;
	


}
