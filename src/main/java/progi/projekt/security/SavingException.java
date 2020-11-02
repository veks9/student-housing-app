package progi.projekt.security;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(I_AM_A_TEAPOT) //koji kod staviti kada DB baci gresku?
public class SavingException extends RuntimeException {
	private static final long serialVersionUID = 10L;

	public SavingException(String message) {
		super(message);
	}
}
