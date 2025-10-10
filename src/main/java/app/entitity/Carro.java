package app.entitity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
   
    @NotNull(message = "Nome não pode ser nulo")
   	private String nome;
       
    private Integer ano;

    // um carro pertence a uma marca
    // uma marca pode ter muitos carros
    @ManyToOne(cascade = CascadeType.ALL) // permite salvar a marca junto com o carro
    @JoinColumn(name="marca_id")
    //@JsonBackReference  // // evita o loop infinito  
    @JsonIgnoreProperties("carros")  // ← Evita loop sem impedir serialização
    private Marca marca;
    
   // private Integer marca_id;
    
//	private String marca;
    
    // um carro pode ter muitos proprietarios
    // um proprietario pode ter muitos carros
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    		name="carro_proprietario",
            joinColumns = @JoinColumn(name="carro_id"),
            inverseJoinColumns = @JoinColumn(name="proprietario_id")
    )
    private List<Proprietario> proprietarios;
    
	private String modelo;

	@Column(name="ano_fabricacao")
	private Integer anoFabricacao;
	


}
