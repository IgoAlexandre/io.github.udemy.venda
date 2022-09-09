package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.exception.PedidoNaoEncontradoException;
import io.github.udemy.venda.exception.RegraNegocioException;
import io.github.udemy.venda.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> listaErros = ex.getAllErrors().stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(listaErros);
    }
}
