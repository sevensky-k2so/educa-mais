package br.com.projeto.educamais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UsuarioNaoAutenticadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoAutenticadoException() {
		super("Usuário não está autenticado.");
	}

}