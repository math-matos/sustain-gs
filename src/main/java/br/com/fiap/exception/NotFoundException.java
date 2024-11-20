package br.com.fiap.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
    }

    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
