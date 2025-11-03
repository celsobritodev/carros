package app.auth;

import lombok.Getter;
import lombok.Setter;


// esta classe não tem @Entity porque ela nao vai ser persistida
@Getter
@Setter
public class Login {

	private String username;
	private String password;
	
}
